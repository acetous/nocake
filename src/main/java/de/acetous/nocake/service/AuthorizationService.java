package de.acetous.nocake.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {

    private String current;

    public String generateToken() {
        return current = UUID.randomUUID().toString();
    }

    public boolean checkToken(String token) {
        if (current == null) {
            return false;
        }
        return current.equals(token);
    }

    public boolean hasToken() {
        return current != null;
    }
}
