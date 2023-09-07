package org.nihongo_deb.DeadLinkSearcher.Services;

import org.nihongo_deb.DeadLinkSearcher.DTO.InputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.DTO.InvalidArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.DTO.OutputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.Exceptions.ArrayURLsValidationException;
import org.nihongo_deb.DeadLinkSearcher.Util.IsAvailableConnection;
import org.nihongo_deb.DeadLinkSearcher.Validators.URLValidator;
import org.springframework.stereotype.Service;

@Service
public class ArrayURLsService {
    private final IsAvailableConnection isAvailableConnection;
    private final URLValidator urlValidator;

    public ArrayURLsService(IsAvailableConnection isAvailableConnection, URLValidator urlValidator) {
        this.isAvailableConnection = isAvailableConnection;
        this.urlValidator = urlValidator;
    }

    public OutputArrayURLsDTO checkURLs (InputArrayURLsDTO inputArrayURLsDTO){
        InvalidArrayURLsDTO invalidArrayURLsDTO = new InvalidArrayURLsDTO();
        OutputArrayURLsDTO outputArrayURLsDTO = new OutputArrayURLsDTO();

        inputArrayURLsDTO.getInputUrls().forEach(url -> {
            if (!urlValidator.isValid(url)){
                invalidArrayURLsDTO.addURL(url);
            }
        });

        if (!invalidArrayURLsDTO.getInvalidURLs().isEmpty())
            throw new ArrayURLsValidationException(invalidArrayURLsDTO);

        inputArrayURLsDTO.getInputUrls().forEach(url -> {
            if (isAvailableConnection.isAvailableURL(url))
                outputArrayURLsDTO.addAvailableURL(url);
            else
                outputArrayURLsDTO.addNotAvailableURL(url);
        });

        return outputArrayURLsDTO;
    }
}
