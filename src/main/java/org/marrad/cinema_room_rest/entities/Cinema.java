package org.marrad.cinema_room_rest.entities;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {
    private int rows;
    private int columns;
    private Map<Seat, UUID> seats = new ConcurrentHashMap<>();

    public Cinema() {
        this.rows = 9;
        this.columns = 9;
        int seatCount = 1;
        while (seatCount <= rows*columns) {
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    this.seats.put(new Seat(i, j, true), UUID.randomUUID());
                    seatCount++;
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Map<Seat, UUID> getSeats() {
        return seats;
    }

    public void setSeats(Map<Seat, UUID> seats) {
        this.seats = seats;
    }
}
