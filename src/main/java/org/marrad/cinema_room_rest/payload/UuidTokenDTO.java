package org.marrad.cinema_room_rest.payload;

import java.util.UUID;

public class UuidTokenDTO {
    private UUID token;

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
