package com.codecool.sherwoodbet.services.adminService;

import com.codecool.sherwoodbet.model.User;
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
        return "userAdmin";
    }


    @RequestMapping("/user/delete/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        System.out.println("toroljeeee");
        userRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/add")
    public String addUser(@RequestParam(value = "ID") String ID, @RequestParam(value = "name") String name,@RequestParam(value = "password") String password,@RequestParam(value = "email") String email){

        System.out.println("adddoljaaa");
        User user = new User(name, password, email);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/edit/{values_id}")
    public String editUser(@PathVariable Integer values_id, @RequestParam(value = "Name") String name,@RequestParam(value = "Password") String password,@RequestParam(value = "Email") String email){

        User user = userRepository.findOne(Long.valueOf(values_id));

        System.out.println("editaljaaaa");
        System.out.println(values_id);

            user.setID(Long.valueOf(values_id));
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        System.out.println("editke kesz");
        return "redirect:/admin/users";
    }






    }
