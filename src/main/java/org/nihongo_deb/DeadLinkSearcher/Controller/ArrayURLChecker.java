package org.nihongo_deb.DeadLinkSearcher.Controller;


import org.nihongo_deb.DeadLinkSearcher.DTO.InputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.DTO.OutputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.Util.IsAvailableConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/array")
public class ArrayURLChecker {

    private final IsAvailableConnection isAvailableConnection;

    @Autowired
    public ArrayURLChecker(IsAvailableConnection isAvailableConnection) {
        this.isAvailableConnection = isAvailableConnection;
    }

    @PostMapping
    public OutputArrayURLsDTO checkURLs(InputArrayURLsDTO urls){
        OutputArrayURLsDTO outputURLs = new OutputArrayURLsDTO();

        urls.getInputUrls().forEach(url -> {
            if (isAvailableConnection.isAvailableURL(url))
                outputURLs.addAvailableURL(url);
            else
                outputURLs.addNotAvailableURL(url);
        });

        return outputURLs;
    }
}
