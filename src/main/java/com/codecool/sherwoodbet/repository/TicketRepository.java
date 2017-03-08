package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by patrik on 2017.03.06..
 */
import org.springframework.stereotype.Repository;

/**
 * Created by csyk on 2017.03.07..
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
