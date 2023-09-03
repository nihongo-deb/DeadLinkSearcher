package org.nihongo_deb.DeadLinkSearcher.Exceptions;

import org.nihongo_deb.DeadLinkSearcher.DTO.InvalidArrayURLsDTO;


public class ArrayURLsValidationException extends RuntimeException{
    private InvalidArrayURLsDTO invalidArrayURLsDTO;

    public ArrayURLsValidationException(InvalidArrayURLsDTO invalidArrayURLsDTO){
        this.invalidArrayURLsDTO = invalidArrayURLsDTO;
    }

    public InvalidArrayURLsDTO getInvalidArrayURLsDTO() {
        return invalidArrayURLsDTO;
    }

    public void setInvalidArrayURLsDTO(InvalidArrayURLsDTO invalidArrayURLsDTO) {
        this.invalidArrayURLsDTO = invalidArrayURLsDTO;
    }

}
