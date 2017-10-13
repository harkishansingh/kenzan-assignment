package com.kenzan.exception;

import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public abstract class CustomExceptionMapper<E extends Throwable> {

    public Response toResponse(E e) {
        Response.Status status = getStatus();
        // View
        ErrorView view = toErrorView(status, e);
        return Response.status(status).entity(view).type(MediaType.APPLICATION_JSON).build();
    }

    public ErrorView toErrorView(Status status, Throwable e) {
        ErrorView view = new ErrorView(status.getStatusCode(), status);
        view.setException(e.getClass());
        view.setMessage(e.getMessage());

        // Stack
        StringWriter errors = new StringWriter();
        view.setStackTrace(errors.toString());
        return view;
    }

    public abstract Response.Status getStatus();
}
