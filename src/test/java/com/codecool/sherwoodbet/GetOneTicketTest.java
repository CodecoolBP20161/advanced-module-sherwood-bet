package com.codecool.sherwoodbet;

import com.codecool.sherwoodbet.controller.TicketController;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by danielbalogh on 3/30/17.
 */
@Component
@SpringBootTest(classes = SherwoodBetApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GetOneTicketTest {

    @Autowired
    TicketRepository ticketRepository;

    @Mock
    TicketController ticketController;

}
