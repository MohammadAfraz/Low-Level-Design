package com.demo.bookmyshow.model;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Date;

public class Show {
    Screen Screen;
    LocalTime showTime;
    Movie movie;

    public Screen getScreen() {
        return Screen;
    }

    public void setScreen(Screen screen) {
        Screen = screen;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
