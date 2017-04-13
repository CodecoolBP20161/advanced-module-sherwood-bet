package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Ticket;
import com.codecool.sherwoodbet.model.database.User;
import com.codecool.sherwoodbet.model.database.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by patrik on 2017.04.07..
 */
@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {


    @Query("SELECT us FROM UserTicket us WHERE us.mode=?1 and us.category=?2 and us.user.name=?3 and us.ticket.id=?4")
    UserTicket multipleFilteringForPlay(String mode, Integer cat, String userName, Long ticketId);

}