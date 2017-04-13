package com.codecool.sherwoodbet.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrik on 2017.04.06..
 */

@Controller
public class SessionEndpointController {


    @RequestMapping("/session_data")
    @ResponseBody
    public Map giveBackDatas(Authentication authentication) {
        HashMap datas = new HashMap<>();
        String userName = authentication.getName();
        datas.put("username", userName);
        datas.put("balance", 123);
        datas.put("rank", "supersayan");
        return datas;
    }

}
