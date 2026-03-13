package org.marrad.cinema_room_rest.entities;

public class CinemaStatistics {
    int income;
    int available;
    int purchased;

    public CinemaStatistics() {
        income = 0;
        available = 0;
        purchased = 0;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
