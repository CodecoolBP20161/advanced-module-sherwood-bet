package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.model.database.Team;
import com.codecool.sherwoodbet.repository.TeamRepository;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by patrik on 2017.03.08..
 */
@Controller
@RequestMapping("/admin")
public class TicketAdminController {

    @Autowired
    TicketRepository ticketRepository;

    @RequestMapping("/tickets")
    public String collectTeams(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());
        return "admin/ticketAdmin";
    }

//    @RequestMapping("/team/add")
//    public String addUser(@RequestParam(value = "stadium") String stadium, @RequestParam(value = "shortName") String shortName, @RequestParam(value = "name") String name){
//
//        System.out.println("adddoljaaa");
//        Team team = new Team(stadium, shortName, name);
//        teamRepository.save(team);
//        return "redirect:/admin/teams";
//    }
//
//    @RequestMapping("/team/delete/{values_id}")
//    public String removeFromDB(@PathVariable Integer values_id){
//        teamRepository.delete(Long.valueOf(values_id));
//        return "redirect:/admin/teams";
//    }
//
//    @RequestMapping("/team/edit")
//    public String editUser(@RequestParam(value = "ID") String ID,@RequestParam(value = "stadium") String stadium,@RequestParam(value = "shortName") String shortName,@RequestParam(value = "name") String name){
//
//        Team team = teamRepository.findOne(Long.valueOf(ID));
//        team.setStadium(stadium);
//        team.setShortName(shortName);
//        team.setName(name);
//        teamRepository.save(team);
//        return "redirect:/admin/teams";
//    }

}
