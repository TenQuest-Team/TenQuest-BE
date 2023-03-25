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

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import util.BeanUtils;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Authentication or Authorization needed");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("Req Auth Header: " + jwtHeader);
        // check if it's valid token format
        if(jwtHeader==null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return ;
        }
        String jwtToken =jwtHeader.replace("Bearer ", "");
        System.out.println("JWT Token: "+ jwtToken);

        String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("id").asString();
        // If JWT Token Authentication is valid, userId might be not null
        if(userId != null){
            System.out.println("valid token, UserId: " + userId);
            Member user = memberRepository.findMemberByUserId(userId).get();
            System.out.println("Member.getUserName: " + user.getUserName().toString());
            PrincipalDetails principalDetails = new PrincipalDetails(user);


            // If JWT Token Authentication is valid => Create Authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request,response);
        }

    }

}
