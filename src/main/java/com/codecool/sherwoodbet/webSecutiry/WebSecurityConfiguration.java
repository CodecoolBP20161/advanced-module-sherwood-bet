package com.codecool.sherwoodbet.webSecutiry;

import com.codecool.sherwoodbet.services.security_service.CustomPasswordAuthenticationFilter;
import com.codecool.sherwoodbet.services.security_service.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /*override the configure method and permit all user to see the login and registration page
    and to log out.*/
    //TODO delete csrf disable when in .js configured properly csrf token
    // TODO: 2017.03.13. create myFailureHandler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/welcome", "/bet/**", "/game").permitAll().anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new CustomPasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .failureHandler(myFailureHandler())
                .and()
                .logout()
                .deleteCookies()
                .logoutUrl("/logout")
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