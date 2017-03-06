package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by csyk on 2017.03.02..
 */
@Entity
@Table(name = "team")
class Team {

    private Long ID;
    private String stadium;
    private Set<Match> awayMatches;
    private Set<Match> homeMatches;

    @Column(unique = true)
    private String short_name;

    @Column(unique = true)
    private String name;

    public Team() {
    }

    public Team(String stadium, String short_name, String name) {
        this.stadium = stadium;
        this.short_name = short_name;
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeTeam")
    public Set<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(Set<Match> matches) {
        this.homeMatches = matches;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "awayTeam")
    public Set<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(Set<Match> matches) {
        this.awayMatches = matches;
    }
}