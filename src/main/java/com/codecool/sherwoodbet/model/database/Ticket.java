package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by csyk on 2017.03.03..
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    private Long ID;
    private String description;
    private Date deadline;

    @Column(unique = true)
    private String title;

    public Ticket() {
    }

    public Ticket(String description, Date deadline, String title) {
        this.description = description;
        this.deadline = deadline;
        this.title = title;
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
}
