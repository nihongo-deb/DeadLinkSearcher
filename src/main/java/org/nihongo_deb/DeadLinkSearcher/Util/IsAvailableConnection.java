package org.nihongo_deb.DeadLinkSearcher.Util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class IsAvailableConnection {
    private static boolean isAvailableURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.connect();
            conn.setConnectTimeout(1_000);
            conn.setReadTimeout(1_000);
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
}
