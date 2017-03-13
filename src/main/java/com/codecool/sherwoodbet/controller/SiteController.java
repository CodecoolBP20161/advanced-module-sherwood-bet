package com.codecool.sherwoodbet.controller;


import com.codecool.sherwoodbet.services.security_service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by patrik on 2017.02.01..
 */
@Controller
public class SiteController {

//    if session exists redirect to index2.html
// TODO: 2017.02.24. answer frontend that succes or failure the login process
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println(session);
        return "index";
    }
}
