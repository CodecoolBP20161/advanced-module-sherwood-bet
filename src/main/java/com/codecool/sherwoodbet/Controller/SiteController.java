package com.codecool.sherwoodbet.Controller;


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

    @RequestMapping("/")
    public String index (Model model){
        return "index";
    }

    //TODO using validation class instead of creating here response hashmap
    @RequestMapping("/signup")
    @ResponseBody
    public Map signup(@RequestBody() String JSON){
        log.info(JSON);
        Map response = new HashMap<String, ArrayList>();
        ArrayList error = new ArrayList();
        error.add("username");
        response.put("errors", error);
        log.info(response.toString());
        return response;
    }
}
