package org.nihongo_deb.DeadLinkSearcher.Exceptions;

import org.nihongo_deb.DeadLinkSearcher.Entity.User;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}
