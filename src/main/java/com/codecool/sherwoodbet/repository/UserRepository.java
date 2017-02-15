package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrik on 2017.02.02..
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
