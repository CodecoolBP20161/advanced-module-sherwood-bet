package com.codecool.sherwoodbet.Service;

import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import com.codecool.sherwoodbet.WebSecutiry.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Created by patrik on 2017.02.03..
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordHashing passwordHashing;


    public void saveUser(String username, String rawPassword, String email){

        String hashedPassword = passwordHashing.encode(rawPassword);
        userRepository.save(new User(username, hashedPassword, email));
    }
}
