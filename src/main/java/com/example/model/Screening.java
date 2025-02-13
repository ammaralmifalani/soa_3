package com.example.model;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private LocalDateTime date;

    public Screening(Movie movie, LocalDateTime date) {
        this.movie = movie;
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getDate() {
        return date;
    }
    
    @Override
    public String toString() {
        return "Screening: " + movie.getTitle() + " on " + date;
    }
}
