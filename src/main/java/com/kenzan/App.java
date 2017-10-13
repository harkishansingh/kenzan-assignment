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

public class App extends Application<Config>{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(App.class);
	private GuiceBundle<Config> guiceBundle;
	
	@Override
	public void initialize(Bootstrap<Config> bootstrap) {
		// guice
		this.guiceBundle = GuiceBundle.<Config> newBuilder()
				.enableAutoConfig(getClass().getPackage().getName())
				// Main Module
				.addModule(new Module())
				// conf
				.setConfigClass(Config.class).build();
		bootstrap.addBundle(this.guiceBundle);
		bootstrap.addBundle(new SwaggerBundle<Config>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config configuration) {
				return configuration.getSwaggerConfig();
			}
		});
	}

	@Override
	public void run(Config configuration, Environment environment) throws Exception {
		environment.jersey()
				.register(new LoggingFilter(java.util.logging.Logger.getLogger(LoggingFilter.class.getName()), true));

		LOGGER.info("registered all bundles");

	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}

}
