package com.codecool.sherwoodbet.Controller;


import com.codecool.sherwoodbet.Service.UserService;
import com.codecool.sherwoodbet.Validate.UserValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/")
    public String index (){
        return "index";
    }

    @RequestMapping("/signup")
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
        }
        System.out.println(userValidate.checkUsername(userName));
        response.put("errors", error);
        log.info(response.toString());

        //if there are errors not save into database
        if(error.isEmpty())
            userService.saveUser(userName, password, email);
        return response;
    }
}
