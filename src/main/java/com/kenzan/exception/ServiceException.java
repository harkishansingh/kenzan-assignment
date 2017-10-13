package com.kenzan.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 7402111120079994982L;

	public ServiceException(Throwable cause, String message, Object... args) {
		super(String.format(message, args), cause);
	}

	public ServiceException(String message, Object... args) {
		super(String.format(message, args));
	}

}