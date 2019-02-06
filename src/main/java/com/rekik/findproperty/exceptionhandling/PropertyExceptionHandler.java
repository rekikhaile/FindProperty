package com.rekik.findproperty.exceptionhandling;

import com.rekik.findproperty.entity.Property;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PropertyExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String handleInvalidInput(InvalidInputException exception){
        return exception.getMessage();

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid input, Please put a JSON Array with exisiting valid property ids!")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public void handleEmptyInput(){
    }
}
