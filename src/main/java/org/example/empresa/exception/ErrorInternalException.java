package org.example.empresa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternalException extends RuntimeException {
    public ErrorInternalException(String message) {
        super(message);
    }
}
