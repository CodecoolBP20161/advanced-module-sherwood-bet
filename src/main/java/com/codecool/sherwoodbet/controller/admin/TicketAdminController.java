package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.model.database.Match;
import com.codecool.sherwoodbet.model.database.Ticket;
import com.codecool.sherwoodbet.repository.MatchRepository;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by patrik on 2017.03.08..
 */
@Controller
@RequestMapping("/admin")
public class TicketAdminController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MatchRepository matchRepository;

    @RequestMapping("/tickets")
    public String collectTeams(Model model){
        model.addAttribute("tickets", ticketRepository.findAll());
        model.addAttribute("matches", matchRepository.findAll());
        return "admin/ticketAdmin";
    }

    @RequestMapping("/ticket/setMatches")
    public String setMatches(@RequestParam(value = "id") String ID,
                             @RequestParam(value = "match1") Match match1,
                             @RequestParam(value = "match2") Match match2,
                             @RequestParam(value = "match3") Match match3,
                             @RequestParam(value = "match4") Match match4,
                             @RequestParam(value = "match5") Match match5,
                             @RequestParam(value = "match6") Match match6,
                             @RequestParam(value = "match7") Match match7){
        HashSet<Match> matches = new HashSet<>(Arrays.asList(match1, match2, match3, match4, match5, match6, match7));
        Ticket ticket = ticketRepository.findOne(Long.valueOf(ID));
        ticket.setMatches(matches);
        return "redirect:/admin/tickets";
    }

    @RequestMapping("/ticket/add")
    public String addUser(@RequestParam(value = "deadline") String deadLine,
                          @RequestParam(value = "title") String title,
                          @RequestParam(value = "description") String description) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy/hh/mm");
        Date date = format.parse(deadLine);
        Ticket ticket = new Ticket(description, date, title);
        ticket.setPlayable(true);
        ticketRepository.save(ticket);
        return "redirect:/admin/tickets";
    }

    @RequestMapping("/ticket/delete/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        ticketRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/tickets";
    }

    @RequestMapping("/ticket/edit")
    public String editUser(@RequestParam(value = "id") String ID,
                           @RequestParam(value = "deadline") String deadline,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "description") String description) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date date = format.parse(deadline);
        Ticket ticket = ticketRepository.findOne(Long.valueOf(ID));
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setDeadline(date);
        ticketRepository.save(ticket);
        return "redirect:/admin/tickets";
    }
}
