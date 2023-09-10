package org.nihongo_deb.DeadLinkSearcher.Exceptions;

public class TelegramIdAlreadyExistsException extends RuntimeException{
    public TelegramIdAlreadyExistsException(String message) {
        super(message);
    }
}
