package com.kns.tenquest.config;

import com.kns.tenquest.filter.secFilter1;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<secFilter1> secFilter1(){
        FilterRegistrationBean<secFilter1> filterRegistrationBean = new FilterRegistrationBean<secFilter1>(new secFilter1());
        filterRegistrationBean.addUrlPatterns("/**");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }
}
