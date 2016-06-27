package de.acetous.nocake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class OpenBrowserService {

    private int port;

    @Autowired
    public OpenBrowserService(@Value("${server.port}") int port) {
        this.port = port;
    }

    public void openUrl(String url) {
        try {
            Desktop.getDesktop().browse(new URI("http://localhost:" + port + url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
