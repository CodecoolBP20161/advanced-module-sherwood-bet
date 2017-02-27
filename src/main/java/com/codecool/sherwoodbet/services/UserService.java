package com.codecool.sherwoodbet.services;

import com.codecool.sherwoodbet.model.User;
import com.codecool.sherwoodbet.repository.UserRepository;
import com.codecool.sherwoodbet.webSecutiry.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by patrik on 2017.02.03..
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordHashing passwordHashing = new PasswordHashing();

    public void saveUser(String username, String rawPassword, String email){

        String hashedPassword = passwordHashing.getHash(rawPassword);
        userRepository.save(new User(username, hashedPassword, email));
    }
}
