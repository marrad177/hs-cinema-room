package org.marrad.cinema_room_rest.services;

public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super("{\"error\":\"" + message + "\"}");
    }}
