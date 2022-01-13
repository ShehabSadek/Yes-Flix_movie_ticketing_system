package Ticket;

import Halls.Halls;
import Halls.Seats;
import Movie.Movie;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Ticket implements Serializable {
    private int id;
    private Halls hall;
    private Seats seat;
    private Date playingDate;
    private Time playingTime;
    private Movie movie;

    public Ticket(Halls hall, Seats seat, Date playingDate, Time playingTime) {
        this.hall = hall;
        this.seat = seat;
        this.playingDate = playingDate;
        this.playingTime = playingTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", playingDate=" + playingDate +
                ", playingTime=" + playingTime +
                ", movie=" + movie.getName() +
                ", seat=" + seat.getSeatNumber() +
                '}';
    }
}