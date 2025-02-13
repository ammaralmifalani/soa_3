package com.example.model;

public class MovieTicket {
    private Screening screening;
    private boolean isPremium;

    public MovieTicket(Screening screening, boolean isPremium) {
        this.screening = screening;
        this.isPremium = isPremium;
    }

    public Screening getScreening() {
        return screening;
    }

    public boolean isPremium() {
        return isPremium;
    }
     @Override
    public String toString() {
        return "MovieTicket: " + screening.getMovie().getTitle() + ", Date: " + screening.getDate() + ", Premium: " + isPremium;
    }
}
