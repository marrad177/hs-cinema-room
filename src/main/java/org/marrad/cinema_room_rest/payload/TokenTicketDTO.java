package org.marrad.cinema_room_rest.payload;

import java.util.UUID;

public class TokenTicketDTO extends TicketDTO{
    UUID token;

    public TokenTicketDTO(SeatDTO seatDTO, UUID token) {
        super(seatDTO);
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
