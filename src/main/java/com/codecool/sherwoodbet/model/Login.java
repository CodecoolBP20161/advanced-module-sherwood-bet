package com.codecool.sherwoodbet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by csyk on 2017.02.15..
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
