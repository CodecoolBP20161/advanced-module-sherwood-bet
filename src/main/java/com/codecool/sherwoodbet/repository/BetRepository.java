package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by csyk on 2017.03.08..
 */
@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
}
