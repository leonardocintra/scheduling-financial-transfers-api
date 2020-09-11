package com.leaolabs.scheduling.commons.exception;

public class MissingParameterException extends BaseControllerException {

    public MissingParameterException(Object... parameters) {
        super(parameters);
    }
}