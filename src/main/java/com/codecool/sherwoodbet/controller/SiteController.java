package com.codecool.sherwoodbet.controller;


import com.codecool.sherwoodbet.model.Signup;
import com.codecool.sherwoodbet.services.Email_service.Controller.EmailAPIController;
import com.codecool.sherwoodbet.services.UserService;
import com.codecool.sherwoodbet.validate.UserValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by patrik on 2017.02.01..
 */
@Controller
@ComponentScan(basePackageClasses = {Controller.class})
public class SiteController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}