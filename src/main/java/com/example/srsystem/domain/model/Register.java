package com.example.srsystem.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Register implements Serializable {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

}
