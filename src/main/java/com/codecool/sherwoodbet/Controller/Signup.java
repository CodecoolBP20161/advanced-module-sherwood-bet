package com.codecool.sherwoodbet.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by csyk on 2017.02.06..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Signup {

    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
