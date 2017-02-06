package com.codecool.sherwoodbet;

import com.codecool.sherwoodbet.Model.User;
import com.codecool.sherwoodbet.Repository.UserRepository;
import com.codecool.sherwoodbet.Validate.UserValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SherwoodBetApplication {

    private static final Logger log = LoggerFactory.getLogger(SherwoodBetApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SherwoodBetApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User());
        };
    }
}



