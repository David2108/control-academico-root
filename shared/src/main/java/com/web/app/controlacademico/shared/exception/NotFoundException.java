package com.web.app.controlacademico.shared.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("No se ha encontrado " + message);
    }
}
