package com.codecool.sherwoodbet.Service;

import org.springframework.stereotype.Service;

public interface SecurityService {
    public String findLoggedInUsername();
    public void autoLogin(String username, String password);
}