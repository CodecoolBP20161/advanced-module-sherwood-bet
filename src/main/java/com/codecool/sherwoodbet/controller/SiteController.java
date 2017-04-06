package com.codecool.sherwoodbet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by patrik on 2017.02.01..
 */

// TODO: 2017.04.06. when we clicked more times with bad datas,
// TODO: get anonymus user(login_successful=true), but fortunately redirect to welcome root
@Controller
public class SiteController {

    @RequestMapping("/")
    public String index(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated() == true) {
            return "wall";
        } else {
            return "index";
        }
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping("/bet")
    public String game() {
        return "game";
    }



}
