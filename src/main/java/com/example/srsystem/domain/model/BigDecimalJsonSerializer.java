package com.example.srsystem.domain.model;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BigDecimalJsonSerializer extends JsonSerializer<BigDecimal>{

    @Override
    public void serialize(BigDecimal arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        if (arg0 != null) {
            arg1.writeString(arg0.toPlainString());
        }
    }
}