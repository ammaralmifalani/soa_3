package com.example.strategies;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import com.example.interfaces.ExportStrategy;
import com.example.service.Order;

public class JsonExportStrategy implements ExportStrategy {
    @Override
    public void export(Order order, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.writeValue(new File(filePath), order);
    }
}



