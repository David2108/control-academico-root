package com.web.app.controlacademico.academic.core.exception;

public class CourseExistsException extends RuntimeException {
    public CourseExistsException(String message) {
        super(message);
    }
}
