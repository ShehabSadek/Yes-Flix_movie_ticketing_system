package Ticket;

import Halls.Halls;
import Halls.Seats;
import Movies.Movies;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Ticket implements Serializable {
    private int id;
    private Halls hall;
    private Seats seat;
    private Date playingDate;
    private Time playingTime;
    private Movies movie;

    public Ticket(Halls hall, Seats seat, Date playingDate, Time playingTime) {
        this.hall = hall;
        this.seat = seat;
        this.playingDate = playingDate;
        this.playingTime = playingTime;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public Halls getHall() {
        return hall;
    }

    public Seats getSeat() {
        return seat;
    }

    public Date getPlayingDate() {
        return playingDate;
    }

    public Time getPlayingTime() {
        return playingTime;
    }

    public void setHall(Halls hall) {
        this.hall = hall;
    }

    public void setSeat(Seats seat) {
        this.seat = seat;
    }

    public void setPlayingDate(Date playingDate) {
        this.playingDate = playingDate;
    }

    public void setPlayingTime(Time playingTime) {
        this.playingTime = playingTime;
    }
}