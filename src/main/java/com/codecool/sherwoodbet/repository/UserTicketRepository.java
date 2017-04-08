package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrik on 2017.04.07..
 */
@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}
