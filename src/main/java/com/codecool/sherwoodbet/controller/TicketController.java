package com.codecool.sherwoodbet.controller;

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

    private List<Map> tickets = new ArrayList<>();
    private Map<String, Object> ticket = new HashMap<>();
    private List<String> matches = new ArrayList<>();

    private Map<String, Object> inticket = new HashMap<>();
    private List<Map> aTicketMatches = new ArrayList<>();
    private Map<String, Object> match = new HashMap<>();
    private Map<String, Integer> bet = new HashMap<>();

    private void creatingDatas() {
        tickets.clear();
        ticket.clear();
        matches.clear();
        inticket.clear();
        aTicketMatches.clear();
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

        inticket.put("title", "Demo");
        inticket.put("deadline", "tomorrow");
        inticket.put("ticket_id", "1");
        inticket.put("playable", "true");
        inticket.put("intro", "blabla");
        inticket.put("matches", aTicketMatches);
        aTicketMatches.add(match);

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
        Map<String, List> response = new HashMap<>();
        creatingDatas();

        response.put("tickets", tickets);
        return response;
    }

    @RequestMapping("/bet/api/get_ticket")
    @ResponseBody
    public Map getTicket(@RequestParam int ticket) {
        return inticket;
    }
}


