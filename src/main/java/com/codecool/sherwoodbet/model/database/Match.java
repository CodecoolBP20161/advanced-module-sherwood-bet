package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by csyk on 2017.03.02..
 */
@Entity
@Table(name = "match")
public class Match {

    private Long ID;
    private Team homeTeam;
    private Team awayTeam;
    private String venue;
    private String league;
    private String round;
    private String link;
    private Date deadLine;
    private String result;
    private Set<Ticket> tickets;

    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, String venue, String league, String round, String link, Date deadLine, String result) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.venue = venue;
        this.league = league;
        this.round = round;
        this.link = link;
        this.deadLine = deadLine;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @ManyToOne
    @JoinColumn(name = "home_team")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "away_team")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @ManyToMany
    @JoinTable(name = "match_of_ticket", joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "match_id")})
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
