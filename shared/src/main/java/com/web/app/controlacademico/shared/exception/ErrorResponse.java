package com.web.app.controlacademico.shared.exception;

import java.time.LocalDateTime;

public record ErrorResponse (int status, String message, LocalDateTime dateTime){
}
