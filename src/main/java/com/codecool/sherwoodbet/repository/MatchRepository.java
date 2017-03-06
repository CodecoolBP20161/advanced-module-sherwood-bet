package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by csyk on 2017.03.02..
 */
@Repository
public interface MatchRepository extends JpaRepository<User, Long> {
}
