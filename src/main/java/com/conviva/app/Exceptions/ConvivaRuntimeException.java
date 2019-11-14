package com.conviva.app.Exceptions;

public class ConvivaRuntimeException extends RuntimeException{
    public ConvivaRuntimeException(){
    }

    public ConvivaRuntimeException(String message) {
        super(message);
    }
}
