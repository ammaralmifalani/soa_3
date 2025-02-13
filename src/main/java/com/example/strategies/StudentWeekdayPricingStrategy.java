package com.example.strategies;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.example.interfaces.PricingStrategy;
import com.example.model.MovieTicket;

public class StudentWeekdayPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<MovieTicket> tickets, Boolean isStudentOrder) {
        if (tickets.isEmpty()) return 0.0;
        
        double total = 0.0;
        boolean isWeekend = !isWeekday(tickets.get(0).getScreening().getDate());
        System.out.println("Weekend? " + isWeekend);
        System.out.println("Student order? " + isStudentOrder);
        
        for (int i = 0; i < tickets.size(); i++) {
            MovieTicket ticket = tickets.get(i);
            double basePrice = ticket.getScreening().getMovie().getStandardPrice();
            boolean isPremium = ticket.isPremium();
            
            double premiumSurcharge = isPremium ? (isStudentOrder ? 2.0 : 3.0) : 0.0;
            double ticketPrice = basePrice + premiumSurcharge;

            // Regel 1: Elk 2e ticket is gratis voor studenten (altijd) of op doordeweekse dagen voor iedereen
            if ((isStudentOrder || !isWeekend) && i % 2 == 1) {
                System.out.println("Gratis ticket toegepast: " + (basePrice + premiumSurcharge));
                continue; 
            }

            total += ticketPrice;
            System.out.println("Ticket prijs: " + ticketPrice);
        }

        return total;
    }

    private boolean isWeekday(LocalDateTime date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }
}