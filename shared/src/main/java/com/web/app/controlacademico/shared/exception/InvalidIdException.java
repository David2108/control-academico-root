package com.web.app.controlacademico.shared.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(){
        super("ID inválido. Debe ser mayor a 0.");
    }
}
