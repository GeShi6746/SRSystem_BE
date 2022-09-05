package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmVCode {
    private String username;

    private int ccode;
}
