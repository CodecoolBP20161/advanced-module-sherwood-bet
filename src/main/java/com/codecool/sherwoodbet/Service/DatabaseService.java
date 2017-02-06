package com.codecool.sherwoodbet.Service;

import com.codecool.sherwoodbet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by patrik on 2017.02.03..
 */
public class DatabaseService {

    @Autowired
    UserRepository userRepository;

    public boolean saveUser(String username, String rawpassword, String email){





        return true;
    }
}
