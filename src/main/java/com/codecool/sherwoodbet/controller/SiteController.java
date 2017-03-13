package com.codecool.sherwoodbet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by patrik on 2017.02.01..
 */
@Controller
public class SiteController {

    // TODO: check cookies authentication
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        if (cookie == null) {
            return "index";
        } else {
            return "index2";
        }
    }
}