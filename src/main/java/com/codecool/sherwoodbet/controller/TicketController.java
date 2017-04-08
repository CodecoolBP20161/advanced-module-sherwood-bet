package com.codecool.sherwoodbet.controller;

import com.codecool.sherwoodbet.model.database.*;
import com.codecool.sherwoodbet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    BetRepository betRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserTicketRepository userTicketRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MatchRepository matchRepository;
    private List<Map> tickets = new ArrayList<>();
    private Map<String, Object> ticket = new HashMap<>();
    private List<String> matches = new ArrayList<>();
    private Map<String, Object> oneTicket = new HashMap<>();
    private List<Map> oneTicketMatches = new ArrayList<>();
    private Map<String, Object> match = new HashMap<>();

    @RequestMapping("/bet/api/playable_tickets")
    @ResponseBody
    public Map playableTickets() {
        List<Ticket> playableTickets = ticketRepository.findByPlayable(true);
        Map<String, List> response = new HashMap<>();
        tickets.clear();
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
    public Map getTicket(@RequestParam Long ticket, Authentication authentication) {
        oneTicket.clear();
        Ticket ticketdb = ticketRepository.findOne(ticket);
        oneTicket.put("intro", ticketdb.getDescription());
        oneTicket.put("title", ticketdb.getTitle());
        oneTicket.put("deadline", ticketdb.getDeadline());
        oneTicket.put("ticket_id", ticketdb.getID());
        oneTicket.put("playable", ticketdb.isPlayable());

        oneTicketMatches.clear();

        String userName = authentication.getName();
        User authUser = userRepository.findByName(userName);


        String mode = "valami";
        Integer category = 25;
        String status = "akarmi";
        Integer result = 0;
        Integer rank = 1;
        Boolean paid = true;
        Float playoff = 1f;
        UserTicket userTicket = new UserTicket(authUser, ticketdb, mode,category,status,result,rank,paid,playoff);
        userTicketRepository.save(userTicket);



        for (Match oneMatch : ticketdb.getMatches()) {
            Bet actualBet = new Bet(ticketdb, oneMatch);
            betRepository.save(actualBet);
            Map<String, Object> match = new HashMap<>();
            match.put("venue", oneMatch.getVenue());
            match.put("round_number", oneMatch.getRound());
            match.put("match", oneMatch.getHomeTeam().getShortName() + "-" + oneMatch.getAwayTeam().getShortName() + ":" + oneMatch.getLeague());
            match.put("more_info", oneMatch.getLink());
            match.put("id", oneMatch.getID());
            match.put("tournament", oneMatch.getLeague());
            match.put("match_date", oneMatch.getDeadLine());

            Map<String, Integer> bet = new HashMap<>();
            bet.put("home", actualBet.getHome());
            bet.put("draw", actualBet.getDraw());
            bet.put("away", actualBet.getAway());
            bet.put("id", Math.toIntExact(actualBet.getID()));
            bet.put("match", Math.toIntExact(oneMatch.getID()));
            bet.put("user_ticket", Math.toIntExact(userTicket.getID()));

            match.put("bet", bet);
            oneTicketMatches.add(match);
        }
        oneTicket.put("matches", oneTicketMatches);

        return oneTicket;
    }
}


