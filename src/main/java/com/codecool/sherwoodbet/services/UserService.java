package com.codecool.sherwoodbet.services;

import com.codecool.sherwoodbet.model.database.Role;
import com.codecool.sherwoodbet.model.database.User;
import com.codecool.sherwoodbet.repository.RoleRepository;
import com.codecool.sherwoodbet.repository.UserRepository;
import com.codecool.sherwoodbet.webSecutiry.PasswordHashing;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by patrik on 2017.02.03..
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordHashing passwordHashing;

    public void saveUser(String username, String rawPassword, String email){
        Role userRole = roleRepository.findByName("user");

        String hashedPassword = passwordHashing.getHash(rawPassword);

        userRepository.save(new User(username, hashedPassword, email, userRole));
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }


}
