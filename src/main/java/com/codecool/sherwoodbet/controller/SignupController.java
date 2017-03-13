package com.codecool.sherwoodbet.controller;

import com.codecool.sherwoodbet.model.Signup;
import com.codecool.sherwoodbet.model.database.User;
import com.codecool.sherwoodbet.repository.UserRepository;
import com.codecool.sherwoodbet.services.UserService;
import com.codecool.sherwoodbet.services.email_service.Controller.EmailAPIController;
import com.codecool.sherwoodbet.validate.UserValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SignupController {

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailAPIController emailAPIController;

    @RequestMapping("/signup")
    @ResponseBody
    public Map signup(@RequestBody Signup signupData) {

        String email = signupData.getEmail();
        String userName = signupData.getUsername();
        String password = signupData.getPassword();
        log.info("e-mail: " + email + " " + "username: " + userName);

        Map<String, List> response = new HashMap<>();
        List error = validation(email, userName, password);

        response.put("errors", error);
        log.info(response.toString());
        //if there are not errors save into database and send a welcome email
        if (error.isEmpty()) {
            userService.saveUser(userName, password, email);
            emailAPIController.sendWelcomeEmail(email, userName);
        }
        return response;
    }

    private List validation(String email, String userName, String password) {
        ArrayList<String> error = new ArrayList<>();
        if (!(userValidate.checkEmail(email) && userValidate.isValidEmail(email))) {
            error.add("email");
        }
        if (!userValidate.checkUsername(userName)) {
            error.add("username");
        }
        return error;
    }
}