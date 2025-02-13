package com.example.service;

import java.io.IOException;

import com.example.interfaces.ExportStrategy;

public class OrderExporter {
    private ExportStrategy strategy;
    
    public OrderExporter(ExportStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(ExportStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void export(Order order, String filePath) throws IOException {
        strategy.export(order, filePath);
    }
}