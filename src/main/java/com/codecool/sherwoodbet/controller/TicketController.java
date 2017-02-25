package com.codecool.sherwoodbet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(SiteController.class);

    @RequestMapping("/bet/api/playable_tickets")
    @ResponseBody
    public Map playableTickets() {
        Map<String, List> response = new HashMap<>();
        List<Map> tickets = new ArrayList<>();
        Map<String, Object> ticket = new HashMap<>();
        List<String> matches = new ArrayList<>();

        matches.add("Békés - Tarhos");
        matches.add("Kamut - Murony");

        ticket.put("title", "Demo");
        ticket.put("intro", "blabla");
        ticket.put("deadline", "tomorrow");
        ticket.put("matches", matches);

        tickets.add(ticket);

        response.put("tickets", tickets);

        return response;
    }

    @RequestMapping("/bet/api/get_ticket")
    @ResponseBody
    public String getTicket(@RequestParam String id) {
        return id + " ticket";
    }
}


