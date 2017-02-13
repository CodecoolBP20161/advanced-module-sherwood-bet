package com.codecool.sherwoodbet;

import com.codecool.sherwoodbet.Repository.UserRepository;
import com.codecool.sherwoodbet.Services.UserService;
import com.codecool.sherwoodbet.Validate.UserValidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by danielbalogh on 08.02.17..
 */
@Component
@SpringBootTest(classes = SherwoodBetApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserValidateTest {

    @Mock
    UserRepository userRepository;

    @Autowired
    UserValidate validate;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository testDatabase;
    private String username = "kissbela";
    private String email = "kissbela@bela.com";
    private String falseName = "13@hu";
    private String falseMail = "jkhk";

    @Before
    public void before() {
        userService.saveUser("danidani", "123456", "dani@dani.com");
    }

    @After
    public void after() {
        testDatabase.deleteAll();
    }

    @Test
    public void checkUsernameTest() {
        userService.saveUser("danidani", "123456", "dani@dani.com");
        boolean answer = true;
        boolean val = validate.checkUsername(username);
        assertEquals(answer, val);
    }

    @Test
    public void checkExistingUsernameTest() {
        boolean answer = false;
        boolean val = validate.checkUsername("danidani");
        assertEquals(answer, val);
    }

    @Test
    public void checkEmailTest() {
        boolean answer = true;
        boolean val = validate.checkEmail(email);
        assertEquals(answer, val);
    }

    @Test
    public void checkExistingEmailTest() {
        boolean answer = false;
        boolean val = validate.checkEmail("dani@dani.com");
        assertEquals(answer, val);
    }

    @Test
    public void isValidEmailTest() {
        boolean answer = true;
        boolean val = validate.isValidEmail(email);
        assertEquals(answer, val);
    }

    @Test
    public void isInvalidEmailTest() {
        boolean answer = false;
        boolean val = validate.isValidEmail(falseMail);
        assertEquals(answer, val);
    }
}