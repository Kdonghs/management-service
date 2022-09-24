package com.stock.managementservice.config.auth.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class sessionUser implements Serializable {
    private String name;
    private String email;

    public sessionUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
