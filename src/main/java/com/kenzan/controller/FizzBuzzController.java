package com.kenzan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.kenzan.handler.IFizzBuzzHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/fizzbuzz", tags = "FizzBuzz", produces = "application/json", consumes = "application/json")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fizzbuzz")
public class FizzBuzzController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FizzBuzzController.class);

	@Context
	private UriInfo uriInfo;
	@Inject
	private IFizzBuzzHandler iFizzBuzzHandler;

	@GET
	@Path("/{parameter}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Takes integer as input and returns fizzbuzz combinations", notes = "Takes integer as input and returns fizzbuzz combinations or error message", consumes = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 500, message = "Internal server error") })

	public Response fetchCombinations(
			@ApiParam(value = "FizzBuzz input field", required = true) @PathParam("parameter") String parameter) {
		LOGGER.info("Params.... parameter: {}", parameter);
		JSONObject response = iFizzBuzzHandler.getFizzBuzzCombinations(parameter);
		
		return Response.status(Status.OK).entity(response.toString()).build();
	}
}
