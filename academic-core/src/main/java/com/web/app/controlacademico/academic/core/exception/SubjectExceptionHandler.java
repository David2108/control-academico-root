package com.web.app.controlacademico.academic.core.exception;

import com.web.app.controlacademico.shared.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.web.app.controlacademico.shared.util.ExceptionUtil.buildError;

@RestControllerAdvice
public class SubjectExceptionHandler {

    @ExceptionHandler(SubjectExistsException.class)
    public ResponseEntity<ErrorResponse> handleSubjectExist(SubjectExistsException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }
}
