package com.codecool.sherwoodbet.repository;

import com.codecool.sherwoodbet.model.database.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by csyk on 2017.02.16..
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
