package com.codecool.sherwoodbet.controller;

import com.codecool.sherwoodbet.model.database.Match;
import com.codecool.sherwoodbet.model.database.Ticket;
import com.codecool.sherwoodbet.repository.MatchRepository;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by csyk on 2017.02.21..
 */
@Controller
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MatchRepository matchRepository;

    @RequestMapping("/bet/api/playable_tickets")
    @ResponseBody
    public Map playableTickets() {
        List<Ticket> playableTickets = ticketRepository.findByPlayable(true);
        Map<String, List> response = new HashMap<>();
        List<Map> tickets = new ArrayList<>();
        for (Ticket oneTicket : playableTickets) {
            Map<String, Object> ticket = new HashMap<>();
            ticket.put("title", oneTicket.getTitle());
            ticket.put("intro", oneTicket.getDescription());
            ticket.put("deadline", oneTicket.getDeadline());
            ArrayList<String> listOfMatches = new ArrayList<>();

            for(Match match:oneTicket.getMatches()){
                String matchName = match.getHomeTeam().getShortName() + "-" + match.getAwayTeam().getShortName() + ":" + match.getLeague();
                listOfMatches.add(matchName);
            }

            ticket.put("matches", listOfMatches);
            ticket.put("ticket_id", oneTicket.getID());
            tickets.add(ticket);
        }

        response.put("tickets", tickets);
        return response;
    }

    @RequestMapping("/bet/api/get_ticket")
    @ResponseBody
    public Map getTicket(@RequestParam Long ticket) {
        Map<String, Object> oneTicket = new HashMap<>();
        Ticket ticketdb = ticketRepository.findOne(ticket);
        oneTicket.put("intro", ticketdb.getDescription());
        oneTicket.put("title", ticketdb.getTitle());
        oneTicket.put("deadline", ticketdb.getDeadline());
        oneTicket.put("ticket_id", ticketdb.getID());
        oneTicket.put("playable", ticketdb.isPlayable());

        List<Map> oneTicketMatches = new ArrayList<>();
        for (Match oneMatch : ticketdb.getMatches()) {
            Map<String, Object> match = new HashMap<>();
            match.put("venue", oneMatch.getVenue());
            match.put("round_number", oneMatch.getRound());
            match.put("match", oneMatch.getHomeTeam().getShortName() + "-" + oneMatch.getAwayTeam().getShortName() + ":" + oneMatch.getLeague());
            match.put("more_info", oneMatch.getLink());
            match.put("id", oneMatch.getID());
            match.put("tournament", oneMatch.getLeague());
            match.put("match_date", oneMatch.getDeadLine());

            Map<String, Integer> bet = new HashMap<>();
            bet.put("home", 0);
            bet.put("draw", 0);
            bet.put("away", 0);
            Random r = new Random();
            int n = r.nextInt(500) + 1;
            bet.put("id", n);

            bet.put("match", 1);
            bet.put("user_ticket", 1);

            match.put("bet", bet);
            oneTicketMatches.add(match);
        }
        oneTicket.put("matches", oneTicketMatches);

        return oneTicket;
    }
}


