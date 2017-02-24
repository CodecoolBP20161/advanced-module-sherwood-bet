package com.codecool.sherwoodbet.webSecutiry;

import com.codecool.sherwoodbet.services.UserService;
import com.codecool.sherwoodbet.services.security_service.CustomPasswordAuthenticationFilter;
//import com.codecool.sherwoodbet.services.security_service.MySavedRequestAwareAuthenticationSuccessHandler;
import com.codecool.sherwoodbet.services.security_service.MySimpleUrlAuthenticationSuccessHandler;
import com.codecool.sherwoodbet.services.security_service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by patrik on 2017.02.03..
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /*override the configure method and permit all user to see the login and registration page
    and to log out.*/
    //TODO delete csrf disable when in .js configured properly csrf token
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/login").permitAll().anyRequest()
                .authenticated()
                .and()
                .addFilterAt(new CustomPasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .permitAll();
        http
                .csrf().disable();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    public MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    //enable every javascript resources at static
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*.js");
    }
}