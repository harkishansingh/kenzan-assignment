package com.kenzan;

import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class FizzBuzzApplication extends Application<FizzBuzzConfiguration>{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FizzBuzzApplication.class);
	private GuiceBundle<FizzBuzzConfiguration> guiceBundle;
	
	@Override
	public void initialize(Bootstrap<FizzBuzzConfiguration> bootstrap) {
		// guice
		this.guiceBundle = GuiceBundle.<FizzBuzzConfiguration> newBuilder()
				.enableAutoConfig(getClass().getPackage().getName())
				// Main Module
				.addModule(new FizzBuzzModule())
				// conf
				.setConfigClass(FizzBuzzConfiguration.class).build();
		bootstrap.addBundle(this.guiceBundle);
		bootstrap.addBundle(new SwaggerBundle<FizzBuzzConfiguration>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(FizzBuzzConfiguration configuration) {
				return configuration.getSwaggerConfig();
			}
		});
	}

	@Override
	public void run(FizzBuzzConfiguration configuration, Environment environment) throws Exception {
		environment.jersey()
				.register(new LoggingFilter(java.util.logging.Logger.getLogger(LoggingFilter.class.getName()), true));

		LOGGER.info("registered all bundles");

	}

	public static void main(String[] args) throws Exception {
		new FizzBuzzApplication().run(args);
	}

}
