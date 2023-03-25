package com.kns.tenquest.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kns.tenquest.auth.PrincipalDetails;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import util.BeanUtils;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        System.out.println("Authentication or Authorization needed");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("Req Auth Header: " + jwtHeader);

        if(jwtHeader==null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return ;
        }
        String jwtToken =jwtHeader.replace("Bearer ", "");
        System.out.println("JWT Token: "+ jwtToken);

        // check jwt token is valid
        String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("id").asString();

        if(userId != null){
            System.out.println("valid token, UserId: " + userId);
            Member user = memberRepository.findMemberByUserId(userId).get();
            System.out.println("Member.getUserName: " + user.getUserName().toString());
            PrincipalDetails principalDetails = new PrincipalDetails(user);


        }

    }

}
