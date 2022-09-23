package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Targets implements Serializable {
    private double target0;

    private double target1;

    private double target2;

}
