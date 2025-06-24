package com.web.app.controlacademico.shared.util;

import com.web.app.controlacademico.shared.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ExceptionUtil {

    public static ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message) {
        return
                ResponseEntity.status(status)
                        .body(new ErrorResponse(
                                status.value(),
                                message,
                                LocalDateTime.now()));
    }

}
