package com.demo.bookmyshow.model;

import com.demo.bookmyshow.model.Movie;
import com.demo.bookmyshow.model.Screen;

import java.time.LocalTime;
public class Show {
    com.demo.bookmyshow.model.Screen Screen;
    LocalTime localDateTime;
    Movie movie;

    public Screen getScreen() {
        return Screen;
    }

    public void setScreen(Screen screen) {
        Screen = screen;
    }

    public LocalTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
