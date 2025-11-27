package com.schottenTotten.model;

// Héritage d'Exception (Slide 108)
public class CoupInvalideException extends Exception {
    public CoupInvalideException(String message) {
        super(message);
    }
}