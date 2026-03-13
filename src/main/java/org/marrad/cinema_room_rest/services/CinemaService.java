package org.marrad.cinema_room_rest.services;

import org.marrad.cinema_room_rest.entities.Cinema;
import org.marrad.cinema_room_rest.entities.Seat;
import org.marrad.cinema_room_rest.entities.CinemaStatistics;
import org.marrad.cinema_room_rest.payload.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class CinemaService {
    public CinemaDTO showCinema(Cinema cinema) {
        SeatDTO[] seatDTOS = cinema.getSeats().entrySet().stream()
                .map(entry -> {
                    return new SeatDTO(entry.getKey().getRow(),
                            entry.getKey().getColumn(),
                            calculatePrice(entry.getKey().getRow()));
                })
                .sorted((seat1, seat2) ->
                        seat1.getRow() > seat2.getRow() ? 1 : (
                                seat1.getRow() == seat2.getRow() &&
                                        seat1.getColumn() > seat2.getColumn() ? 1 : -1))
                .toArray(SeatDTO[]::new);
        return new CinemaDTO(cinema.getRows(), cinema.getColumns(), seatDTOS);
    }

    public int calculatePrice(int row) {
        return row <= 4 ? 10 : 8;
    }

    public TokenTicketDTO buyTicket(Seat chosenSeat, Cinema cinema) {
        if (chosenSeat.getRow() > cinema.getRows() || chosenSeat.getColumn() > cinema.getColumns()
                || chosenSeat.getRow() <= 0 || chosenSeat.getColumn() <= 0) {
            throw new TicketException("The number of a row or a column is out of bounds!");
        }
        UUID token = cinema.getSeats().get(chosenSeat);
        Seat avilableSeat = cinema.getSeats().keySet().stream()
                .filter(seat -> seat.equals(chosenSeat))
                .findFirst()
                .get();
        if (avilableSeat.isAvailable()) {
            SeatDTO seatDTO =
                    new SeatDTO(avilableSeat.getRow(), avilableSeat.getColumn(), calculatePrice(avilableSeat.getRow()));
            TokenTicketDTO tokenTicketDTO = new TokenTicketDTO(seatDTO, token);
            avilableSeat.setAvailable(false);
            return tokenTicketDTO;
        } else {
            throw new TicketException("The ticket has been already purchased!");
        }
    }

    public TicketDTO returnTicket(Cinema cinema, UUID token) {
        if (token == null) {
            throw new TicketException("Wrong token!");
        } else if (cinema.getSeats().containsValue(token)) {
            TicketDTO ticketDTO = new TicketDTO();
            Set<Map.Entry<Seat, UUID>> cinemaSeats = cinema.getSeats().entrySet();
            cinemaSeats.forEach(entry -> {
                if (entry.getValue().equals(token)) {
                    int row = entry.getKey().getRow();
                    int column = entry.getKey().getColumn();
                    int price = calculatePrice(entry.getKey().getRow());
                    entry.getKey().setAvailable(true);
                    ticketDTO.setTicket(new SeatDTO(row, column, price));
                    cinema.getSeats().put(entry.getKey(), UUID.randomUUID());
                }
            });
            return ticketDTO;
        } else {
            throw new TicketException("Wrong token!");
        }
    }

    public long getAvailableSeats(Cinema cinema) {
        return cinema.getSeats().keySet().stream()
                .filter(entry -> entry.isAvailable())
                .count();
    }

    public StatsDTO getStats(Cinema cinema, CinemaStatistics cinemaStatistics, String password) {
        if(password.equals("super_secret")) {
            StatsDTO statsDTO = new StatsDTO(cinemaStatistics.getIncome(),
                    getAvailableSeats(cinema),
                    cinemaStatistics.getPurchased());
            return statsDTO;
        } else {
            throw new PasswordException("The password is wrong!");
        }

    }
}
