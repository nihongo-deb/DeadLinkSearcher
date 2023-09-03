package org.nihongo_deb.DeadLinkSearcher.Exceptions.Hendlers;

import org.nihongo_deb.DeadLinkSearcher.Exceptions.ArrayURLsValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArrayURLsServiceExceptionHandler {

    @ExceptionHandler(ArrayURLsValidationException.class)
    public ResponseEntity<?> handleArrayURLsValidationException(ArrayURLsValidationException ex){
        return new ResponseEntity<>(ex.getInvalidArrayURLsDTO(), HttpStatus.BAD_REQUEST);
    }
}
