package com.codecool.sherwoodbet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by csyk on 2017.02.21..
 */
@Controller
public class TicketController {

    @RequestMapping("/bet/api/playable_tickets")
    @ResponseBody
    public String playableTickets() {
        return "tickets....";
    }

    @RequestMapping("/bet/api/get_ticket")
    @ResponseBody
    public String getTicket(@RequestParam String id) {
        return id + " ticket";
    }
}


