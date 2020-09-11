package com.leaolabs.scheduling.commons.exception;

public class EntityNotFoundException extends BaseControllerException {

    public EntityNotFoundException(Object... parameters) {
        super(parameters);
    }
}
