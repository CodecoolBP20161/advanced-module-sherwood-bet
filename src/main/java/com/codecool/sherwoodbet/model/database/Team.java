package com.codecool.sherwoodbet.model.database;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by csyk on 2017.03.02..
 */
// it's avoid infinite recursion.........
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
@Table(name = "team")
public class Team {

    private Long ID;
    private String stadium;
    private Set<Match> awayMatches;
    private Set<Match> homeMatches;

    @Column(unique = true)
    private String shortName;

    @Column(unique = true)
    private String name;

    public Team() {
    }

    public Team(String stadium, String shortName, String name) {
        this.stadium = stadium;
        this.shortName = shortName;
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    @OneToMany(mappedBy = "homeTeam")
    public Set<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(Set<Match> matches) {
        this.homeMatches = matches;
    }

    @OneToMany(mappedBy = "awayTeam")
    public Set<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(Set<Match> matches) {
        this.awayMatches = matches;
    }
}