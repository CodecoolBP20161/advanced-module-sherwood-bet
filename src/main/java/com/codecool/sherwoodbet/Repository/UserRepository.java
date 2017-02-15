package com.codecool.sherwoodbet.Repository;

import com.codecool.sherwoodbet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrik on 2017.02.02..
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
