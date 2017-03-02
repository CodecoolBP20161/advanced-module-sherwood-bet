package com.codecool.sherwoodbet.services.security_service;

import com.codecool.sherwoodbet.model.Login;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by csyk on 2017.02.20..
 */
// TODO: 2017.02.24. should add SimpleGrantedAuthority user's role as a parameter outside from this class 
public class CustomPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            BufferedReader reader = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line;
            line = reader.readLine();
            System.out.println(" 1 " + line);

            ObjectMapper mapper = new ObjectMapper();
            Login authReq = mapper.readValue(line, Login.class);
//            System.out.println(authReq.getUsername());
//            authReq.setRole(authReq.getUsername());
//            System.out.println(authReq.getRole());

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("user"));

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword(), grantedAuths);
            System.out.println("token is authenticated: " + token.isAuthenticated());
            return token;

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public class AuthReq {
        private UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

        private String username;
        private String password;
        private String role;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRole() {
            this.role = userDetailsService.userRepository.findByName(username).getRole().getName();
        }

        public String getRole() {
            return role;
        }
    }
}