package org.marrad.cinema_room_rest.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TicketException extends RuntimeException {
    public TicketException(String message) {
        super("{\"error\":\"" + message + "\"}");
    }
}
