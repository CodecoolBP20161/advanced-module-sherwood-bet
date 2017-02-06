package com.codecool.sherwoodbet.Controller;


import com.codecool.sherwoodbet.Validate.UserValidate;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private UserValidate userValidate = new UserValidate();

    @RequestMapping("/")
    public String index (Model model){
        return "index";
    }

    @RequestMapping("/signup")
    @ResponseBody
    public Map signup(@RequestBody Signup signupData){
        String email = signupData.getEmail();
        String userName = signupData.getUsername();
        log.info(email + " " + userName);
        Map response = new HashMap<String, ArrayList>();
        ArrayList error = new ArrayList();
        if(!(userValidate.checkEmail(email) && userValidate.isValidEmail(email))) {
            error.add("email");
        }
        if(!userValidate.checkUsername(userName)){
            error.add("username");
        }
        response.put("errors", error);
        log.info(response.toString());
        return response;
    }
}
