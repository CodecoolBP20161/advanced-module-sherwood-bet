package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by patrik on 2017.03.13..
 */

@Controller
@RequestMapping("admin")
public class BetAdminController {

    @Autowired
    BetRepository betRepository;

    @RequestMapping("/bets")
    public String collectTeams(Model model){
        model.addAttribute("bets", betRepository.findAll());
        return "admin/betAdmin";
    }
}
