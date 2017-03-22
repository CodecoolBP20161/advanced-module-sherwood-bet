package com.codecool.sherwoodbet.services.security_service;

import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

/**
 * Created by csyk on 2017.03.22..
 */
public class CustomLoginPageGeneratingFilter extends DefaultLoginPageGeneratingFilter {

    public CustomLoginPageGeneratingFilter(){
        setFailureUrl("/notsuccessful");
    }
}
