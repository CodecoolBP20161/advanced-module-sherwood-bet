package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.model.database.Team;
import com.codecool.sherwoodbet.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by patrik on 2017.03.06..
 */
@Controller
@RequestMapping("/admin")
public class TeamAdminController {

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/teams")
    public String collectTeams(Model model){
        model.addAttribute("teams", teamRepository.findAll());
        return "admin/teamAdmin";
    }

    @RequestMapping("/team/add")
    public String addTeam(@RequestParam(value = "stadium") String stadium,
                          @RequestParam(value = "shortName") String shortName,
                          @RequestParam(value = "name") String name){

        Team team = new Team(stadium, shortName, name);
        teamRepository.save(team);
        return "redirect:/admin/teams";
    }

    @RequestMapping("/team/delete/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        teamRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/teams";
    }

    @RequestMapping("/team/edit")
    public String editUser(@RequestParam(value = "ID") String ID,
                           @RequestParam(value = "stadium") String stadium,
                           @RequestParam(value = "shortName") String shortName,
                           @RequestParam(value = "name") String name){

        Team team = teamRepository.findOne(Long.valueOf(ID));
        team.setStadium(stadium);
        team.setShortName(shortName);
        team.setName(name);
        teamRepository.save(team);
        return "redirect:/admin/teams";
    }
}
