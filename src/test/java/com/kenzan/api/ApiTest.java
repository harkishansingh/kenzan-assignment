package com.kenzan.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiTest extends AbstractRestApiTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);
	private static final String FIZZBUZZ = "/fizzbuzz";
	private static final String PARAMETER = "100";
	
	
	@BeforeClass
	public void beforeClass() {
		RULE.before();
	}
	
	@AfterClass
	public void afterClass() {
		RULE.after();
	}
	
	@Test(priority = 1)
	public void getCombinationsTest() {
		try {
			Response response = client.target(String.format(HOST, RULE.getLocalPort())).path(FIZZBUZZ).path(PARAMETER)
					.request(MediaType.APPLICATION_JSON).get();

			Assert.assertEquals(response.getStatus(), 200);

		} catch (Exception e) {
			LOGGER.error("failed to parse response json string :{} \n stack strace :{}", e.getMessage(), e);
			Assert.fail();
		}
	}
}
