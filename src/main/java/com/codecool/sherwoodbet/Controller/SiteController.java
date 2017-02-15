package com.codecool.sherwoodbet.Controller;


import com.codecool.sherwoodbet.Model.Login;
import com.codecool.sherwoodbet.Model.Signup;
import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import com.codecool.sherwoodbet.Service.UserService;
import com.codecool.sherwoodbet.Validate.UserValidate;
import com.codecool.sherwoodbet.WebSecutiry.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by patrik on 2017.02.01..
 */
@Controller
public class SiteController {

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordHashing passwordHashing;


    @Autowired
    private UserRepository userRepository;



    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public String index (){
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public Map signup(@RequestBody Signup signupData){
        String email = signupData.getEmail();
        String userName = signupData.getUsername();
        String password = signupData.getPassword();
        log.info(email + " " + userName);
        Map response = new HashMap<String, ArrayList>();
        ArrayList error = new ArrayList();
        if(!(userValidate.checkEmail(email) && userValidate.isValidEmail(email))) {
            error.add("email");
        }
        if(!userValidate.checkUsername(userName)){
            error.add("username");
            System.out.println("lol");
        }
        response.put("errors", error);
        log.info(response.toString());

        //if there are errors not save into database
        if(error.isEmpty())
            userService.saveUser(userName, password, email);
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody Login login){
        Map response = new HashMap<String, Boolean>();
        String username = login.getUsername();
        String password = login.getPassword();
        User user = userRepository.findByUserName(username);
        Boolean equal = false;
        try{
            if(Objects.equals(user.getName(), username) && passwordHashing.matches(password, user.getPassword())){
                equal = true;
            }
        } catch (NullPointerException e){
            log.info("user not exists");
            log.debug("meeeeee");
            System.out.println("lol");
        }
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            Authentication auth = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        } catch (BadCredentialsException ex) {
//
//        }
        response.put("login_successful", equal);
        return response;
    }
}
