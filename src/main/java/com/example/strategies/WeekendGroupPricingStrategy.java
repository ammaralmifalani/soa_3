package com.example.strategies;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.example.interfaces.PricingStrategy;
import com.example.model.MovieTicket;

public class WeekendGroupPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<MovieTicket> tickets, Boolean isStudentOrder) {
        if (tickets.isEmpty()) return 0.0;

        double total = 0.0;
        boolean isWeekend = !isWeekday(tickets.get(0).getScreening().getDate());
        int ticketCount = tickets.size();
        // System.out.println("Weekend? " + isWeekend);
        // System.out.println("Student order? " + isStudentOrder);
        for (MovieTicket ticket : tickets) {
            double basePrice = ticket.getScreening().getMovie().getStandardPrice();
            // System.out.println("Ticket prijs: " + basePrice);
            // System.out.println("Is premium: " + ticket.isPremium());

            double premiumSurcharge = 0.0;
            boolean isPremium = ticket.isPremium();
            if (isPremium) {
                premiumSurcharge = isStudentOrder ? 2.0 : 3.0;
            }
            double ticketPrice = basePrice + premiumSurcharge;

            // System.out.println("Oorspronkelijke ticketprijs: " + ticketPrice);

            boolean isEligibleForGroupDiscount = isWeekend && !isStudentOrder && ticketCount >= 6;

            if (isEligibleForGroupDiscount) {
                ticketPrice *= 0.9; 
                // System.out.println("Groepskorting toegepast: " + ticketPrice);
            }

            total += ticketPrice;
        }

        return total;
    }

   private boolean isWeekday(LocalDateTime date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }
    
}

