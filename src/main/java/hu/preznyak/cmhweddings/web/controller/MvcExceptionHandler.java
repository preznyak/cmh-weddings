package hu.preznyak.cmhweddings.web.controller;

import hu.preznyak.cmhweddings.web.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomException> entityNotFoundErrorHandler(EntityNotFoundException ex) {

        CustomException customException = new CustomException("Entity not found.", ex.getMessage());
        return new ResponseEntity<>(customException, HttpStatus.BAD_REQUEST);
    }

}

