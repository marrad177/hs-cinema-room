package org.marrad.cinema_room_rest.payload;

public class StatsDTO {
    int income;
    long available;
    int purchased;

    public StatsDTO(int income, long available, int purchased) {
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
