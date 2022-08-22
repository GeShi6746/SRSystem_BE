package com.example.srsystem.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ChangePassword implements Serializable {
    private String username;

    private String password;
}
