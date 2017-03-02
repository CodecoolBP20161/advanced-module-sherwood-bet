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
public class AdminServiceController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public String collectUsers(Model model){

        ArrayList<ArrayList<String>> listOfObjectsElements = new ArrayList();

        for(User user: userRepository.findAll()){
            ArrayList<String> listOfValue = new ArrayList();
            listOfValue.add(user.getID().toString());
            listOfValue.add(user.getName());
            listOfValue.add(user.getPassword());
            listOfValue.add(user.getEmail());
            listOfObjectsElements.add(listOfValue);
        }

        String url = "user/add";



        userRepository.findOne(1l);
        model.addAttribute("fields",userRepository.findOne(1l).collectFields());
        model.addAttribute("objectsValues", listOfObjectsElements);
        model.addAttribute("url", url);
        return "admin";
    }


    @RequestMapping("/removefromDB/{values_id}")
    public String removeFromDB(@PathVariable Integer values_id){
        System.out.println("toroljeeee");
        userRepository.delete(Long.valueOf(values_id));
        return "redirect:/admin/users";
    }

    @RequestMapping("/user/add")
    public String addUser(@RequestParam(value = "ID") String ID, @RequestParam(value = "Name") String name,@RequestParam(value = "Password") String password,@RequestParam(value = "Email") String email){

        System.out.println("adddoljaaa");
        User user = new User(name, password, email);
        userRepository.save(user);



        return "redirect:/admin/users";
    }
//
//    @RequestMapping("/user/edit")
//    public String editUser()






    }
