package com.codecool.sherwoodbet.model;

import com.codecool.sherwoodbet.repository.UserRepository;
import com.codecool.sherwoodbet.services.security_service.UserDetailsServiceImpl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by csyk on 2017.02.15..
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Login {

    @Autowired
    private UserRepository userRepository;

    private String username;
    private String password;
    private String role;

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

    //why get nullpointerexception
    public void setRole(String username){this.role = userRepository.findByName(username).getRole().getName();}

    public String getRole(){return role;}
}
