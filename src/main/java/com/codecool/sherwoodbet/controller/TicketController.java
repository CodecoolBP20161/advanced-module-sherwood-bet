package com.codecool.sherwoodbet.controller;

import com.codecool.sherwoodbet.model.database.Match;
import com.codecool.sherwoodbet.model.database.Ticket;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by csyk on 2017.02.21..
 */
@Controller
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    private List<Map> tickets = new ArrayList<>();
    private Map<String, Object> ticket = new HashMap<>();
    private List<String> matches = new ArrayList<>();
    private Map<String, Object> oneTicket = new HashMap<>();
    private List<Map> oneTicketMatches = new ArrayList<>();
    private Map<String, Object> match = new HashMap<>();
    private Map<String, Integer> bet = new HashMap<>();

    private void creatingDatas() {
        tickets.clear();
        ticket.clear();
        matches.clear();
        oneTicket.clear();
        oneTicketMatches.clear();
        match.clear();
        bet.clear();

        matches.add("Békés - Tarhos");
        matches.add("Kamut - Murony");
        matches.add("Brutál MÁV - bárki");

        ticket.put("title", "Demo");
        ticket.put("intro", "blabla");
        ticket.put("deadline", "tomorrow");
        ticket.put("matches", matches);
        ticket.put("ticket_id", 1);

        oneTicket.put("title", "Demo");
        oneTicket.put("deadline", "tomorrow");
        oneTicket.put("ticket_id", "1");
        oneTicket.put("playable", "true");
        oneTicket.put("intro", "blabla");
        oneTicket.put("matches", oneTicketMatches);
        oneTicketMatches.add(match);

        match.put("id", 1);
        match.put("match", "Békés - Tarhos");
        match.put("venue", "Békés");
        match.put("tournament", "edző meccs");
        match.put("round_number", "1");
        match.put("more_info", "asdfjlklé");
        match.put("match_date", "today");
        match.put("bet", bet);

        bet.put("home", 0);
        bet.put("draw", 0);
        bet.put("away", 0);
        bet.put("id", 1);
        bet.put("match", 1);
        bet.put("user_ticket", 1);

        tickets.add(ticket);
    }

    @RequestMapping("/bet/api/playable_tickets")
    @ResponseBody
    public Map playableTickets() {
        List<Ticket> playableTickets = ticketRepository.findByPlayable(true);
        Map<String, List> response = new HashMap<>();
        tickets.clear();
//        creatingDatas();
        for (Ticket oneTicket : playableTickets) {
            Map<String, Object> ticket = new HashMap<>();
            ticket.put("title", oneTicket.getTitle());
            ticket.put("intro", oneTicket.getDescription());
            ticket.put("deadline", oneTicket.getDeadline());
            ticket.put("matches", oneTicket.getMatches());
            ticket.put("ticket_id", oneTicket.getID());
            tickets.add(ticket);
        }

        response.put("tickets", tickets);
        return response;
    }

    @RequestMapping("/bet/api/get_ticket")
    @ResponseBody
    public Map getTicket(@RequestParam Long ticket) {
//        creatingDatas();
        oneTicket.clear();
        Ticket ticketdb = ticketRepository.findOne(ticket);
        oneTicket.put("intro", ticketdb.getDescription());
        oneTicket.put("title", ticketdb.getTitle());
        oneTicket.put("deadline", ticketdb.getDeadline());
        oneTicket.put("ticket_id", ticketdb.getID());
        oneTicket.put("matches", oneTicketMatches);
        oneTicket.put("playable", ticketdb.isPlayable());

        oneTicketMatches.clear();
        for (Match oneMatch : ticketdb.getMatches()) {
            Map<String, Object> match = new HashMap<>();
            match.put("venue", oneMatch.getVenue());
            match.put("round_number", oneMatch.getRound());
            match.put("match", oneMatch.getHomeTeam() + "" + oneMatch.getAwayTeam());
            match.put("more_info", oneMatch.getLink());
            match.put("id", oneMatch.getID());
            match.put("tournament", oneMatch.getLeague());
            match.put("match_date", oneMatch.getDeadLine());

            bet.clear();
            bet.put("home", 0);
            bet.put("draw", 0);
            bet.put("away", 0);
            bet.put("id", 1);
            bet.put("match", 1);
            bet.put("user_ticket", 1);

            match.put("bet", bet);
            oneTicketMatches.add(match);
        }

        return oneTicket;
    }
}


