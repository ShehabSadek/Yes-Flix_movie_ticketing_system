package Halls;

import java.io.Serializable;

public class Seats implements Serializable {
    public static enum tiers {
        VIP, Premium, Normal
    }
    private tiers seatTier;
    private int seatNumber;
    private boolean Taken;

    public Seats(tiers seatTier, int seatNumber) {
        this.seatTier = seatTier;
        this.seatNumber = seatNumber;
    }

    public Seats(tiers seatTier, int seatNumber, boolean taken) {
        this.seatTier = seatTier;
        this.seatNumber = seatNumber;
        Taken = taken;
    }

    public tiers getSeatTier() {
        return seatTier;
    }

    public void setSeatTier(tiers seatTier) {
        this.seatTier = seatTier;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isTaken(){
        return Taken;
    }

    public void setTaken(boolean taken) {
        Taken = taken;
    }

    @Override
    public String toString() {
        return(  " ___ \n" +
                "| "+seatNumber+" |\n" +
                "`````");
    }

}
