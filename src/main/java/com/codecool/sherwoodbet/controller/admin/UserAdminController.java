package com.codecool.sherwoodbet.controller.admin;

import com.codecool.sherwoodbet.model.database.Team;
import com.codecool.sherwoodbet.model.database.User;
import com.codecool.sherwoodbet.repository.TeamRepository;
import com.codecool.sherwoodbet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by patrik on 2017.02.27..
 */

@Controller
@RequestMapping("/admin")
public class UserAdminController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public String collectUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "admin/userAdmin";
    }


    @RequestMapping("/user/delete/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        System.out.println("toroljeeee");
        userRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/add")
    public String addUser(@RequestParam(value = "name") String name,@RequestParam(value = "password") String password,@RequestParam(value = "email") String email){

        System.out.println("adddoljaaa");
        User user = new User(name, password, email);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/edit")
    public String editUser(@RequestParam(value = "ID") String ID,@RequestParam(value = "name") String name,@RequestParam(value = "password") String password,@RequestParam(value = "email") String email){

        User user = userRepository.findOne(Long.valueOf(ID));

        System.out.println("editaljaaaa");
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        System.out.println("editke kesz");
        return "redirect:/admin/users";
    }






    }
