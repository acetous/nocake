package de.acetous.nocake.service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenBrowserService {

    private int port;

    @Autowired
    public OpenBrowserService(@Value("${server.port}") int port) {
        this.port = port;
    }

    public void openUrl(String path) {
        try {
            Desktop.getDesktop().browse(new URI("http", null, "localhost", port, path, null, null));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
