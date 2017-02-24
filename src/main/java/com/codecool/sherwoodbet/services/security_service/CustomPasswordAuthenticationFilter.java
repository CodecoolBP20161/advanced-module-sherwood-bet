package com.codecool.sherwoodbet.services.security_service;

import com.codecool.sherwoodbet.controller.LoginController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by csyk on 2017.02.20..
 */
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
                    UsernamePasswordAuthenticationToken token =
                            new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword());
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