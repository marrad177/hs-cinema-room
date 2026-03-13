package org.marrad.cinema_room_rest.controller;

import org.marrad.cinema_room_rest.entities.Cinema;
import org.marrad.cinema_room_rest.entities.Seat;
import org.marrad.cinema_room_rest.entities.CinemaStatistics;
import org.marrad.cinema_room_rest.payload.TicketDTO;
import org.marrad.cinema_room_rest.payload.TokenTicketDTO;
import org.marrad.cinema_room_rest.payload.UuidTokenDTO;
import org.marrad.cinema_room_rest.services.CinemaService;
import org.marrad.cinema_room_rest.services.PasswordException;
import org.marrad.cinema_room_rest.services.TicketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaController {
    @Autowired
    CinemaService cinemaService;
    Cinema cinema;
    CinemaStatistics cinemaStatistics;

    public CinemaController() {
        cinema = new Cinema();
        cinemaStatistics = new CinemaStatistics();
        cinemaStatistics.setAvailable(cinema.getRows()*cinema.getColumns());
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getCinema() {
        return ResponseEntity.ok().body(cinemaService.showCinema(cinema));
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        try {
            TokenTicketDTO tokenTicketDTO = cinemaService.buyTicket(seat, cinema);
            int tempPurchased = cinemaStatistics.getPurchased();
            cinemaStatistics.setPurchased(tempPurchased + 1);
            int tempIncome = cinemaStatistics.getIncome();
            cinemaStatistics.setIncome(tempIncome + tokenTicketDTO.getTicket().getPrice());
            return ResponseEntity.ok().body(tokenTicketDTO);
        } catch(TicketException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody UuidTokenDTO token) {
        try {
            TicketDTO ticketDTO = cinemaService.returnTicket(cinema, token.getToken());
            int tempPurchased = cinemaStatistics.getPurchased();
            cinemaStatistics.setPurchased(tempPurchased - 1);
            int tempIncome = cinemaStatistics.getIncome();
            cinemaStatistics.setIncome(tempIncome - ticketDTO.getTicket().getPrice());
            return ResponseEntity.ok().body(ticketDTO);
        } catch(TicketException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(defaultValue = "") String password) {
        try {
            return ResponseEntity.ok().body(cinemaService.getStats(cinema, cinemaStatistics, password));
        } catch(PasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
