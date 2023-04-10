package com.marinb.notesapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> BadRequestTreatment (BadRequestException badRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((badRequestException.getMessage()));
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> ResourceNotFoundTreatment (ResourceNotFoundException rnfe) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }
}
