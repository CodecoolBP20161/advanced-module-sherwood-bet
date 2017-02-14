package com.codecool.sherwoodbet.Services;

import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import com.codecool.sherwoodbet.WebSecutiry.PasswordHashing;
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
