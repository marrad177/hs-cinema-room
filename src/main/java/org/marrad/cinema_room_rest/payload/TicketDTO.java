package org.marrad.cinema_room_rest.payload;

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
