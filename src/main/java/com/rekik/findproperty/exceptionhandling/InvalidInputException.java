package com.rekik.findproperty.exceptionhandling;

public class InvalidInputException extends RuntimeException {

    private String message;

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
