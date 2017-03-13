package com.codecool.sherwoodbet.services.email_service.Controller;

import com.codecool.sherwoodbet.services.email_service.Service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by csyk on 2017.02.10..
 */
@Component
public class EmailAPIController {

    private final APIService apiService;

    public EmailAPIController(){
        this.apiService = APIService.getInstance();
    }

    public void sendWelcomeEmail(String to, String name){
        String text = "Welcome to Sherwood-bet!\n Enjoy and win!";
        String subject = "Welcome" + " " + name;

        apiService.sendEmail(to, subject, text);
    }
}