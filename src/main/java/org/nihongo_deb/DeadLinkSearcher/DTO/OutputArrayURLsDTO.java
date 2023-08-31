package org.nihongo_deb.DeadLinkSearcher.DTO;

import java.util.ArrayList;
import java.util.List;

public class OutputArrayURLsDTO {
    private List<String> availableURLs = new ArrayList<>();
    private List<String> notAvailableURLs = new ArrayList<>();

    public List<String> getAvailableURLs() {
        return availableURLs;
    }

    public void setAvailableURLs(List<String> availableURLs) {
        this.availableURLs = availableURLs;
    }

    public List<String> getNotAvailableURLs() {
        return notAvailableURLs;
    }

    public void setNotAvailableURLs(List<String> notAvailableURLs) {
        this.notAvailableURLs = notAvailableURLs;
    }

    public void addAvailableURL(String url){
        this.availableURLs.add(url);
    }

    public void addNotAvailableURL(String url){
        this.notAvailableURLs.add(url);
    }
}
