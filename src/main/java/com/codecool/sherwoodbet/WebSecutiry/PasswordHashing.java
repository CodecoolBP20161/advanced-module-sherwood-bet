package com.codecool.sherwoodbet.WebSecutiry;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by patrik on 2017.02.10..
 */

@Component
public class PasswordHashing{

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String password){
        return passwordEncoder.encode(password);
    }

    public boolean matches(String rawPassword, String encode){
        return passwordEncoder.matches(rawPassword, encode);
    }
}
