package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;

/**
 * Created by patrik on 2017.04.07..
 */
@Entity
@Table(name="userTicket")
public class UserTicket {


    private Long ID;
    private User user;
    private Ticket ticket;
    private String mode;
    private Integer category;
    private String status;
    private Integer result;
    private Integer rank;
    private Boolean paid;
    private Float payoff;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getID() {
        return ID;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name="ticket_id")
    public Ticket getTicket() {
        return ticket;
    }

    public String getMode() {
        return mode;
    }

    public Integer getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public Integer getResult() {
        return result;
    }

    public Integer getRank() {
        return rank;
    }

    public Boolean getPaid() {
        return paid;
    }

    public Float getPayoff() {
        return payoff;
    }


    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setPayoff(Float payoff) {
        this.payoff = payoff;
    }
}
