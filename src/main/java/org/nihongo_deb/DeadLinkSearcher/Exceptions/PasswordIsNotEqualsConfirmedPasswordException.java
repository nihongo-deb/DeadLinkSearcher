package org.nihongo_deb.DeadLinkSearcher.Exceptions;

public class PasswordIsNotEqualsConfirmedPasswordException extends RuntimeException {
    public PasswordIsNotEqualsConfirmedPasswordException(String message){
        super(message);
    }
}
