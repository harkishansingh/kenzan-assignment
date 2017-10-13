package com.kenzan;

import com.google.inject.AbstractModule;
import com.kenzan.handler.FizzBuzzHandler;
import com.kenzan.handler.IFizzBuzzHandler;
import com.kenzan.service.FizzBuzzService;
import com.kenzan.service.IFizzBuzzService;

public class Module extends AbstractModule {

	@Override
	protected void configure() {

		// add services || handlers
		bind(IFizzBuzzHandler.class).to(FizzBuzzHandler.class);
		bind(IFizzBuzzService.class).to(FizzBuzzService.class);

	}

}
