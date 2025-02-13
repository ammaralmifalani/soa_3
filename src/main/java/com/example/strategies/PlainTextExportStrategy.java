package com.example.strategies;

import java.io.FileWriter;
import java.io.IOException;

import com.example.interfaces.ExportStrategy;
import com.example.model.MovieTicket;
import com.example.service.Order;

public class PlainTextExportStrategy implements ExportStrategy {
    @Override
    public void export(Order order, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Order Details:\n");
            for (MovieTicket ticket : order.getTickets()) {
                writer.write(ticket.toString() + "\n");
            }
            writer.write("Total Price: " + order.calculatePrice() + "\n");
        }
    }
}