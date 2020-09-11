package com.leaolabs.scheduling.commons.exception;

public class BaseControllerException extends RuntimeException {

    private final Object[] parameters;

    public BaseControllerException(final Object... parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return this.parameters.clone();
    }
}

