package com.codecool.sherwoodbet.services.security_service;

import com.codecool.sherwoodbet.controller.LoginController;
import com.codecool.sherwoodbet.repository.UserRepository;
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
import org.springframework.stereotype.Service;

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
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    System.out.println(line);
                }
                String parsedReq = sb.toString();
                if (parsedReq != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    AuthReq authReq = mapper.readValue(parsedReq, AuthReq.class);

                    List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
                    String userName = authReq.getUsername();
                    grantedAuths.add(new SimpleGrantedAuthority("user"));
//                    if(userDetailsServiceImpl.loadUserByUsername(userName).getRole().getName().equals("user")) {
//                        grantedAuths.add(new SimpleGrantedAuthority("user"));
//                    }
//                    else if(userDetailsServiceImpl.userRepository.findByName(userName).getRole().getName().equals("admin")){
//                        grantedAuths.add(new SimpleGrantedAuthority("admin"));
//                    }

                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword(), grantedAuths);
                    System.out.println(token.isAuthenticated());
                    return token;
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Data
        public static class AuthReq {
            String username;
            String password;

            public String getUsername() {
                return username;
            }

            public String getPassword() {
                return password;
            }
        }
    }