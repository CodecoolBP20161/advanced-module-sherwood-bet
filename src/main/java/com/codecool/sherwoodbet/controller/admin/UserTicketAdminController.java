package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.repository.BetRepository;
import com.codecool.sherwoodbet.repository.UserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by patrik on 2017.04.07..
 */

@Controller
@RequestMapping("admin")
public class UserTicketAdminController {

    @Autowired
    UserTicketRepository userTicketRepository;

    @RequestMapping("/userTickets")
    public String collectTeams(Model model){
        model.addAttribute("userTickets", userTicketRepository.findAll());
        return "admin/userTicketAdmin";
    }

}
