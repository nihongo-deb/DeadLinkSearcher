package org.nihongo_deb.DeadLinkSearcher.Validators;

import org.springframework.stereotype.Component;

@Component
public class URLValidator {
    private static String REGEXP = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public boolean isValid(String url) {
        return url.matches(REGEXP);
    }
}
