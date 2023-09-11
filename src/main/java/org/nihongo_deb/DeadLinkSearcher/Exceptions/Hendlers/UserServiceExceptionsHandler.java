package org.nihongo_deb.DeadLinkSearcher.Exceptions.Hendlers;

import org.nihongo_deb.DeadLinkSearcher.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserServiceExceptionsHandler {
    @ExceptionHandler(PasswordIsNotEqualsConfirmedPasswordException.class)
    public ResponseEntity<?> handlePasswordIsNotEqualsConfirmedPasswordException(PasswordIsNotEqualsConfirmedPasswordException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelegramIdAlreadyExistsException.class)
    public ResponseEntity<?> handleTelegramIdAlreadyExistsException(TelegramIdAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
