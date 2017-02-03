package com.codecool.sherwoodbet.Repository;

import com.codecool.sherwoodbet.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by patrik on 2017.02.02..
 */
public interface UserRepository extends CrudRepository<User, Long> {
    //List<User> findByLastName(String lastName);
}
