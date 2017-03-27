package com.codecool.sherwoodbet.model.database;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by patrik on 2017.02.02..
 */
@Entity
@Table(name="users")
public class User {

    private Long ID;
    @Column(unique = true)
    private String name;
    private String password;
    private Role role;

    @Column(unique = true)
    private String email;

    public User(){}

    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne
    @JoinColumn(name="role_id")
    public Role getRole() { return role; }

    public void setRole(Role role) {
        this.role = role;
    }
    public ArrayList<String> collectFields(){
        ArrayList<String> listOfFields = new ArrayList();
        listOfFields.add("ID");
        listOfFields.add("Name");
        listOfFields.add("Password");
        listOfFields.add("Email");
        return listOfFields;
    }
}
