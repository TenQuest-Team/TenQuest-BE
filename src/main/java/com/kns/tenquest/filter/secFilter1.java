package com.kns.tenquest.filter;
import jakarta.servlet.*;

import java.io.IOException;

public class secFilter1 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Sec Filter 1");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
