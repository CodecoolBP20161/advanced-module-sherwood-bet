package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;

/**
 * Created by csyk on 2017.03.07..
 */
@Entity
@Table(name = "bet")
public class Bet {

    private Long ID;
    private Ticket userTicket;
    private int away;
    private int draw;
    private int home;
    private Match match;

    public Bet() {
        away = 0;
        draw = 0;
        home = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    @ManyToOne
    @JoinColumn(name = "user_ticket")
    public Ticket getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(Ticket userTicket) {
        this.userTicket = userTicket;
    }

    @ManyToOne
    @JoinColumn(name = "match")
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
