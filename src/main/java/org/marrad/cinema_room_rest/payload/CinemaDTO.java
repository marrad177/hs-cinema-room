package org.marrad.cinema_room_rest.payload;

public class CinemaDTO {
    int rows;
    int columns;
    SeatDTO[] seats;

    public CinemaDTO(int rows, int columns,  SeatDTO[] seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
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

    public SeatDTO[] getSeats() {
        return seats;
    }

    public void setSeats(SeatDTO[] seats) {
        this.seats = seats;
    }
}
