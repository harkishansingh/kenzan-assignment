package com.kenzan;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class Config extends Configuration {

	@NotNull
	@JsonProperty("swagger")
	private SwaggerBundleConfiguration swaggerBundleConfiguration;

	public SwaggerBundleConfiguration getSwaggerConfig() {
		return this.swaggerBundleConfiguration;
	}

}
