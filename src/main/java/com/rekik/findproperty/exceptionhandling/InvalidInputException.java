package com.rekik.findproperty.exceptionhandling;

public class InvalidInputException extends RuntimeException {

    private String message = "lorum ipsum";

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
