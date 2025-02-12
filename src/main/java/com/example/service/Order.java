package com.example.service;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.model.MovieTicket;

public class Order {
    private List<MovieTicket> tickets = new ArrayList<>();
    private boolean isStudentOrder;

    public Order(boolean isStudentOrder) {
        this.isStudentOrder = isStudentOrder;
    }

    public void addTicket(MovieTicket ticket) {
        tickets.add(ticket);
    }

    public double calculatePrice() {
    if (tickets.isEmpty()) return 0.0; // Geen tickets, geen kosten

    double total = 0.0;
    boolean isWeekday = isWeekday(tickets.get(0).getScreening().getDate());
    int ticketCount = tickets.size();

    for (int i = 0; i < ticketCount; i++) {
        MovieTicket ticket = tickets.get(i);
        double basePrice = ticket.getScreening().getMovie().getStandardPrice();
        double premiumSurcharge = ticket.isPremium() ? (isStudentOrder ? 2.0 : 3.0) : 0.0;

        // **Regel 1: Elke 2e ticket is gratis voor studenten of op doordeweekse dagen**
        if ((isStudentOrder || isWeekday) && i % 2 == 1) {
            continue; // Sla het toevoegen van de prijs over (gratis ticket)
        }

        double ticketPrice = basePrice + premiumSurcharge;

        // **Regel 2: Groepskorting van 10% bij 6+ tickets in het weekend voor niet-studenten**
        if (!isWeekday && !isStudentOrder && ticketCount >= 6) {
            ticketPrice *= 0.9; // 10% korting toepassen
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
