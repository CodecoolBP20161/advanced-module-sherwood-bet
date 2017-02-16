package com.codecool.sherwoodbet.controller;

import com.codecool.sherwoodbet.model.Login;
import com.codecool.sherwoodbet.model.User;
import com.codecool.sherwoodbet.repository.UserRepository;
import com.codecool.sherwoodbet.webSecutiry.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by csyk on 2017.02.15..
 */
@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashing passwordHashing;

    @RequestMapping("/login")
    @ResponseBody
    public Map login(@RequestBody Login login) {
        Map response = new HashMap<String, Boolean>();
        String username = login.getUsername();
        String password = login.getPassword();
        log.info(username + password);
        User user = userRepository.findByName(username);
        Boolean equal = false;
        try {
            if (Objects.equals(user.getName(), username) && passwordHashing.matches(password, user.getPassword())) {
                equal = true;
            }
        } catch (NullPointerException e) {
            log.info("user not exists");
        }
        response.put("login_successful", equal);
        log.info(response.toString());
        return response;
    }
}
