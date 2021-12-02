package com.ltran.ecm.rest;

import com.ltran.ecm.exception.ContactNotFoundException;
import com.ltran.ecm.exception.EnterpriseNotFoundException;
import com.ltran.ecm.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Object> handleBadRequest(InvalidInputException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ContactNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(ContactNotFoundException ex) {
        return new ResponseEntity<>("Contact not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EnterpriseNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(EnterpriseNotFoundException ex) {
        return new ResponseEntity<>("Enterprise not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
