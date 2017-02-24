package com.codecool.sherwoodbet.webSecutiry;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by csyk on 2017.02.08..
 */
@Component
public class PasswordHashing {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String getHash(String password){
        return passwordEncoder.encode(password);
    }
}
