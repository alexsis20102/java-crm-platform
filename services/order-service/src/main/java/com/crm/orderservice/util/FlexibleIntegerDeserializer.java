package com.crm.orderservice.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class FlexibleIntegerDeserializer extends JsonDeserializer<Integer>{
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null;
        }

        value = value.replace(",", ".");
        try {
            Float f = Float.parseFloat(value);
            return f.intValue();
        } catch (NumberFormatException e) {
            throw new IOException("Invalid integer value: " + value);
        }
    }
}
