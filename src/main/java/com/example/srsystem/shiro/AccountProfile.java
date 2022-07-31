package com.example.srsystem.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private long userId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

}