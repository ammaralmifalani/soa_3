package com.example.interfaces;

import java.util.List;

import com.example.model.MovieTicket;

 public interface  PricingStrategy {
    double calculatePrice(List<MovieTicket> tickets,Boolean isStudentOrder);
}
