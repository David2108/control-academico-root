package com.web.app.controlacademico.academic.core.exception;

import jakarta.validation.constraints.NotEmpty;

public class SubjectExistsException extends RuntimeException {
    public SubjectExistsException(@NotEmpty String message) {
        super(message);
    }
}
