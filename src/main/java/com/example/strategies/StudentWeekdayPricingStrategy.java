package com.example.strategies;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.example.interfaces.PricingStrategy;
import com.example.model.MovieTicket;

public class StudentWeekdayPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(List<MovieTicket> tickets, boolean isStudentOrder) {
        if (tickets.isEmpty()) return 0.0;
        
        double total = 0.0;
        boolean isWeekend = isWeekday(tickets.get(0).getScreening().getDate());
      
        
        for (int i = 0; i < tickets.size(); i++) {
            MovieTicket ticket = tickets.get(i);
            double basePrice = ticket.getScreening().getMovie().getStandardPrice();
            boolean isPremium = ticket.isPremium();
            
            double premiumSurcharge = 0.0;
            if (isPremium) {
                premiumSurcharge = isStudentOrder ? 2.0 : 3.0;
            }

            double ticketPrice = basePrice + premiumSurcharge;

            boolean isEligibleForFreeTicket = isStudentOrder || !isWeekend;
            boolean isEverySecondTicket = (i % 2 == 1);
            
            if (isEligibleForFreeTicket && isEverySecondTicket) {
                continue; 
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