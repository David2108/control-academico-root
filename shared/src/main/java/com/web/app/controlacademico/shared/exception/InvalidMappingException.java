package com.web.app.controlacademico.shared.exception;

public class InvalidMappingException extends RuntimeException {
    public InvalidMappingException(String message) {
        super("Course not created: " + message );
    }
}
