package com.conviva.app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ConvivaRuntimeException extends RuntimeException{
    public ConvivaRuntimeException(){
    }

    public ConvivaRuntimeException(String message) {
        super(message);
    }
}
