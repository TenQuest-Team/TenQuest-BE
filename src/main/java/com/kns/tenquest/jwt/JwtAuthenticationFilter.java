package com.kns.tenquest.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kns.tenquest.entity.Member;
import io.micrometer.observation.annotation.Observed;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

//            BufferedReader reqReader = request.getReader();
//            String input = null;
//            while((input= reqReader.readLine()) != null)
//                System.out.println(input);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(messageBody);
            String requestBodyUserName = jsonObj.get("username").toString();
            String requestBodyUserPassword = jsonObj.get("password").toString();

            System.out.println("RequestBody[\"username\"]: " + requestBodyUserName); //test complete
            System.out.println("RequestBody[\"password\"]: " + requestBodyUserPassword); //test complete

            Member member = Member.builder()
                    .userId(requestBodyUserName)
                    .userInfo(requestBodyUserPassword)
                    .build();

            System.out.println("member.userId: " + member.getUserId()); //test complete
            System.out.println("member.password: " + member.getUserInfo()); //test complete


        }catch(Exception e){
            e.printStackTrace();
        }
        return super.attemptAuthentication(request, response);
    }

}
