package com.codecool.sherwoodbet.Validate;

import com.codecool.sherwoodbet.Controller.SiteController;
import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


/**
 * Created by patrik on 2017.02.03..
 */

@Component
public class UserValidate {

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private UserRepository userRepository;

    public UserValidate() {
    }


    public boolean checkUsername(String username) {
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(username)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkEmail(String email) {
            for (User user : userRepository.findAll()) {
                if (user.getEmail().equals(email)) {
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