package org.marrad.cinema_room_rest.payload;

import org.marrad.cinema_room_rest.entities.Seat;

import java.util.UUID;

public class TicketDTO {
    SeatDTO ticket;

    public TicketDTO() {}

    public TicketDTO(SeatDTO seatDTO) {
        this.ticket = seatDTO;
    }

        public SeatDTO getTicket() {
        return ticket;
    }

    public void setTicket(SeatDTO seatDTO) {
        this.ticket = seatDTO;
    }
}
