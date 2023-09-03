package org.nihongo_deb.DeadLinkSearcher.DTO;

import java.util.ArrayList;
import java.util.List;

public class InvalidArrayURLsDTO {
    List<String> invalidURLs = new ArrayList<>();

    public List<String> getInvalidURLs() {
        return invalidURLs;
    }
    public void setInvalidURLs(List<String> invalidURLs) {
        this.invalidURLs = invalidURLs;
    }

    public void addURL(String url){
        this.invalidURLs.add(url);
    }
}
