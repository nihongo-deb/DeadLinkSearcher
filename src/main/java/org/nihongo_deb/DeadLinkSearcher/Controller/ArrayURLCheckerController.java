package org.nihongo_deb.DeadLinkSearcher.Controller;

import jakarta.validation.Valid;
import org.nihongo_deb.DeadLinkSearcher.DTO.InputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.DTO.OutputArrayURLsDTO;
import org.nihongo_deb.DeadLinkSearcher.Services.ArrayURLsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/array")
public class ArrayURLCheckerController {
    private final ArrayURLsService arrayURLsService;

    @Autowired
    public ArrayURLCheckerController(ArrayURLsService arrayURLsService) {
        this.arrayURLsService = arrayURLsService;
    }

    @PostMapping
    public OutputArrayURLsDTO checkURLs(InputArrayURLsDTO inputArrayURLsDTO){
        return arrayURLsService.checkURLs(inputArrayURLsDTO);
    }
}
