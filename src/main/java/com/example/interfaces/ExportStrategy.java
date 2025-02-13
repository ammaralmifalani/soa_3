package com.example.interfaces;

import java.io.IOException;

import com.example.service.Order;

public interface ExportStrategy {
    public  void export(Order order, String filePath) throws IOException;
}
