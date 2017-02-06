package com.codecool.sherwoodbet.Validate;

import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by patrik on 2017.02.03..
 */
public class UserValidate {

    @Autowired
    UserRepository userRepository;

    public boolean checkUsername(String username) {

        for (User user : userRepository.findAll()) {
            if (user.getName() == username) {
                return false;
            }
        }
        return true;
    }

    public boolean checkEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail() == email) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}

