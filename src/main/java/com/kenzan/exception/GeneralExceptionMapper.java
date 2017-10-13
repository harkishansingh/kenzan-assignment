package com.kenzan.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.inject.Singleton;

@Provider
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class GeneralExceptionMapper extends CustomExceptionMapper<RuntimeException>
		implements ExceptionMapper<RuntimeException> {

	@Override
	public Status getStatus() {
		return Response.Status.INTERNAL_SERVER_ERROR;
	}

}
