package com.codecool.sherwoodbet.webSecutiry;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by patrik on 2017.02.03..
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*override the configure method and permit all user to see the login and registration page
    and to log out.*/
    //TODO delete csrf disable when in .js configured properly csrf token
    // TODO: 2017.02.25. /bet/** and /game route permite for loged in users
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/bet/**", "/game","/admin/**", "/removefromDB/**").permitAll().anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http
                .csrf().disable();
    }

//    enable every javascript resources at static
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/*.js");
    }
}
