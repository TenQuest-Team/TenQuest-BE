package com.kns.tenquest.filter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class secFilter1 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("[>] Security Filter1");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        // System.out.println("sec filter 1");

        /* get "Authorization" field value from header */
        String authField = req.getHeader("Authorization");
        System.out.println("Auth: " + authField);

        if(authField!=null && authField.equals("sec"))
        {
            res.getWriter().println("Auth Failed"); // for check
            chain.doFilter(req,res);
        }
        else res.getWriter().println("Auth Failed"); // for check

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
