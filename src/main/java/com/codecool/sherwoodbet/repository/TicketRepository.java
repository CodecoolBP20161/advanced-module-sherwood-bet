package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by csyk on 2017.03.07..
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByPlayable(boolean playable);
}
