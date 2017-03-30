package com.codecool.sherwoodbet;

import com.codecool.sherwoodbet.controller.TicketController;
import com.codecool.sherwoodbet.repository.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by danielbalogh on 3/30/17.
 */
@Component
@SpringBootTest(classes = SherwoodBetApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GetOneTicketTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketController ticketController;

//    private final MockMvc mockMvc = standaloneSetup(new AddressController())
//            .build();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void validate_get_address() throws Exception {

        mockMvc.perform(get("/bet/api/get_ticket"))
                .andExpect(status().isOk());

    }
}
