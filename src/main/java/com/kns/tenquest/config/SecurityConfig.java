package com.kns.tenquest.config;
import com.kns.tenquest.filter.secFilter;
import com.kns.tenquest.jwt.JwtAuthenticationFilter;
import com.kns.tenquest.jwt.JwtAuthorizationFilter;
import com.kns.tenquest.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final MemberRepository memberRepository;

    public SecurityConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new secFilter(), SecurityContextPersistenceFilter.class);
        http.csrf().disable().authorizeHttpRequests();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new MyCustom())
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/sec/**").authenticated()
                //.requestMatchers("/sec/**").authenticated()
                .anyRequest().permitAll();
        return http.build();
    }

    public class MyCustom extends AbstractHttpConfigurer<MyCustom, HttpSecurity>{

        @Override
        public void configure(HttpSecurity http) throws Exception {


            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http.addFilter(new JwtAuthenticationFilter(authenticationManager));
            http.addFilter(new JwtAuthorizationFilter(authenticationManager,memberRepository));
        }
    }
}
