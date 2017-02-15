package com.codecool.sherwoodbet.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by patrik on 2017.02.10..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
