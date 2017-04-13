package com.codecool.sherwoodbet.services;

import com.codecool.sherwoodbet.model.database.UserTicket;
import com.codecool.sherwoodbet.repository.UserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by patrik on 2017.04.08..
 */
@Service
public class TicketService {

    @Autowired
    UserTicketRepository userTicketRepository;




    public void calculateResult(UserTicket userTicket){



    }
}
