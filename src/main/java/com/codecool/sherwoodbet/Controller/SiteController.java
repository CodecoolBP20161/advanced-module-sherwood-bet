package com.codecool.sherwoodbet.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by patrik on 2017.02.01..
 */
@Controller
public class SiteController {

    @RequestMapping("/")
    public String index (Model model){
        return "index";
    }
}
