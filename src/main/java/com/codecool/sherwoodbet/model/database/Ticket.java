package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by csyk on 2017.03.03..
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    private Long ID;
    private String description;
    private Date deadline;
    private boolean playable;
    private Set<Bet> userTicket;
    private Set<Match> matches;


    @Column(unique = true)
    private String title;

    public Ticket() {
    }

    public Ticket(String description, Date deadline, String title) {
        this.description = description;
        this.deadline = deadline;
        this.title = title;
        this.matches = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    @OneToMany(mappedBy = "userTicket")
    public Set<Bet> getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(Set<Bet> userTicket) {
        this.userTicket = userTicket;
    }

    @ManyToMany
    @JoinTable(name = "match_of_ticket", joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "match_id")})
    public Set<Match> getMatches() {
        return matches;
    }

//    thats way it fails
    public void setMatches(Set<Match> match) {
        this.matches = match;
    }
}
