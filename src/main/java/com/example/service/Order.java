package com.example.service;
import java.util.ArrayList;
import java.util.List;

import com.example.interfaces.PricingStrategy;
import com.example.model.MovieTicket;

public class Order {
    private List<MovieTicket> tickets = new ArrayList<>();
    private PricingStrategy pricingStrategy;
    private boolean isStudentOrder; 

    public Order(PricingStrategy pricingStrategy, boolean isStudentOrder) {
        this.pricingStrategy = pricingStrategy;
        this.isStudentOrder = isStudentOrder;
    }

    public void addTicket(MovieTicket ticket) {
        tickets.add(ticket);
    }

    public double calculatePrice() {
        return pricingStrategy.calculatePrice(tickets, isStudentOrder);
    }
    public List<MovieTicket> getTickets() {
        return tickets;
    }
    
    public boolean isStudentOrder() {
        return isStudentOrder;
    }
}

