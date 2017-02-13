package com.codecool.sherwoodbet.Model;

import javax.persistence.*;

/**
 * Created by danielbalogh on 13.02.17..
 */
@Entity
@Table(name = "users")
public class TestUser {
    private Long ID;
    private String name;
    private String password;

    @Column(unique = true)
    private String email;


    public TestUser() {
    }

    public TestUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
