package com.example.model;

public class Movie {
    private String title;
    private double standardPrice;

    public Movie(String title, double standardPrice) {
        this.title = title;
        this.standardPrice = standardPrice;
    }

    public String getTitle() {
        return title;
    }

    public double getStandardPrice() {
        return standardPrice;
    }
}
