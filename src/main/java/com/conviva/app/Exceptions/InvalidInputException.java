package com.conviva.app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidInputException extends ConvivaRuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
