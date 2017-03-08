package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.model.database.Match;
import com.codecool.sherwoodbet.model.database.Team;
import com.codecool.sherwoodbet.repository.MatchRepository;
import com.codecool.sherwoodbet.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by patrik on 2017.03.06..
 */
@Controller
@RequestMapping("/admin")
public class MatchAdminController {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;

    @RequestMapping("/matches")
    public String collectMatches(Model model){
        model.addAttribute("matches", matchRepository.findAll());
        model.addAttribute("teams", teamRepository.findAll());
        return "admin/matchAdmin";
    }

    @RequestMapping("/match/add")
    public String addUser(@RequestParam(value = "homeTeam") String homeTeam,
                          @RequestParam(value = "awayTeam") String awayTeam,
                          @RequestParam(value = "venue") String venue,
                          @RequestParam(value = "league") String league,
                          @RequestParam(value = "round") String round,
                          @RequestParam(value = "link") String link,
                          @RequestParam(value = "deadLine") String deadline) throws ParseException {

        Team homeTeamObject = teamRepository.findOne(Long.valueOf(homeTeam));
        Team awayTeamObject = teamRepository.findOne(Long.valueOf(awayTeam));
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy/hh/mm");
        Date date = format.parse(deadline);
        Match match = new Match(homeTeamObject,awayTeamObject,venue,league,round,link,date,result);
        matchRepository.save(match);
        return "redirect:/admin/matches";
    }


    @RequestMapping("/match/delete/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        System.out.println("toroljeeee");
        matchRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/matches";
    }

    @RequestMapping("/match/edit")
    public String editUser(@RequestParam(value = "ID") String ID,
                           @RequestParam(value = "homeTeam") String homeTeam,
                           @RequestParam(value = "awayTeam") String awayTeam,
                           @RequestParam(value = "venue") String venue,
                           @RequestParam(value = "league") String league,
                           @RequestParam(value = "round") String round,
                           @RequestParam(value = "link") String link,
                           @RequestParam(value = "deadLine") String deadline,
                           @RequestParam(value = "result") String result) throws ParseException {
        System.out.println(deadline);
        Match match = matchRepository.findOne(Long.valueOf(ID));
        Team homeTeamObject = teamRepository.findByShortName(homeTeam);
        Team awayTeamObject = teamRepository.findByShortName(awayTeam);
        System.out.println(homeTeamObject);
        System.out.println(awayTeamObject);
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy/hh/mm");
//        Date date = format.parse(deadline);

        match.setHomeTeam(homeTeamObject);
        match.setAwayTeam(awayTeamObject);
        match.setVenue(venue);
        match.setLeague(league);
        match.setRound(round);
        match.setLink(link);
//        match.setDeadLine(date);
        match.setResult(result);
        matchRepository.save(match);
        return "redirect:/admin/matches";
    }
}
