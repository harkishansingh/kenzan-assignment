package com.kenzan.api;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.kenzan.FizzBuzzApplication;
import com.kenzan.FizzBuzzConfiguration;

import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;

/**
 * Custom test configuration can be passed as a VM argument, for example
 * -DtestRestApiConfiguration=test-my-custom-config.yml
 */
public abstract class AbstractRestApiTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestApiTest.class);

	protected static final String HOST = "http://localhost:%d";
	protected static final ObjectMapper mapper = new ObjectMapper();

	private static String DEFAULT_CONFIG_PATH = "config-test.yml";	

	protected static String getConfigFile() {
		String fullPath = ResourceHelpers.resourceFilePath(DEFAULT_CONFIG_PATH);
		LOGGER.info("REST Api test: Full configuration path:" + fullPath);
		return fullPath;
	}

	protected static final DropwizardTestSupport<FizzBuzzConfiguration> RULE = new DropwizardTestSupport<FizzBuzzConfiguration>(
			FizzBuzzApplication.class, getConfigFile(),
			ConfigOverride.config("server.applicationConnectors[0].port", "9190"));

	

	private static Client getClient() {
		Client client = ClientBuilder.newClient().register(MultiPartFeature.class);
		return client;

	}

	protected static final Client client = getClient();

	protected void checkResponseDataSize(Response response, int expectedSize) throws IOException {
		String responseAsString = response.readEntity(String.class);
		JsonNode responseJsonNode = mapper.readValue(responseAsString, JsonNode.class);
		ArrayNode data = (ArrayNode) responseJsonNode.get("data");
		Assert.assertEquals(data.size(), expectedSize);
	}

	protected void checkResponseMessageContent(Response response, String expectedContent) throws IOException {
		String responseAsString = response.readEntity(String.class);
		JsonNode responseJsonNode = mapper.readValue(responseAsString, JsonNode.class);
		JsonNode message = responseJsonNode.get("message");
		Assert.assertTrue(message.toString().contains(expectedContent));
	}
}
