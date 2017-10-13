package com.kenzan.exception;

import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.core.Response.Status;

public class ErrorView extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6376050112929360739L;

	public ErrorView(int code, Status status) {
		put("code", code);// http status code
		put("status", status);// http status string
		put("date", new Date().getTime());// current date
	}

	public ErrorView setMessage(String message) {
		put("message", message);// user message
		return this;
	}

	public ErrorView setStackTrace(String stackTrace) {
		put("stackTrace", stackTrace); // developer message
		return this;
	}

	public ErrorView setException(Class<?> exception) {
		put("exception", exception);
		return this;
	}

}