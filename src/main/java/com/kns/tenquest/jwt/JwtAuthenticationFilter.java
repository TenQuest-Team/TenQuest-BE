package com.kns.tenquest.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kns.tenquest.entity.Member;
import io.micrometer.observation.annotation.Observed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.SortedMap;

// UsernamePasswordAuthenticationFilter runs when /login(post) request
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("Login Request Occur!");
        /* get user's id, password from login request */
//        try {
//            BufferedReader reqReader = request.getReader();
//            String input = null;
//            while((input= reqReader.readLine()) != null)
//                System.out.println(input);
//            }
//        catch (IOException e) {e.printStackTrace();}

        /* mapping Json type user id,pw information to Member Entity */
        try {
            //JSONParser jsonParser = new JSONParser();

        }catch(Exception e){
            e.printStackTrace();
        }
        return super.attemptAuthentication(request, response);
    }

}
