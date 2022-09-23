package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RiskRange implements Serializable {
    private int minValue;

    private int maxValue;
}
