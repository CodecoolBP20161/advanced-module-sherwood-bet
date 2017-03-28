package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by csyk on 2017.03.02..
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByShortName(String name);
}
