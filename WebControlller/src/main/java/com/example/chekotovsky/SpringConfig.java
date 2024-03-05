package com.example.chekotovsky;

import com.example.chekotovsky.Security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Dto.UserDetailsDto;
import org.service.Mappers.Mapper;
import org.service.Mappers.OrderOrderDtoMapper;
import org.service.Mappers.ProductProductDtoMapper;
import org.service.Mappers.UserUserDetailsDtoMapper;
import org.service.Security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan(basePackages= { "org.db", "org.service", "com.example.chekotovsky"})
@EnableAutoConfiguration
@EnableWebSecurity
@EnableJpaRepositories(basePackages="org.db")
@EntityScan(basePackages= { "org.db", "org.service", "com.example.chekotovsky"})
@RequiredArgsConstructor
public class SpringConfig {
    private final ApplicationContext context;
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                    .httpBasic()
                    .and()
                    .cors(AbstractHttpConfigurer::disable)
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .formLogin(AbstractHttpConfigurer::disable)
                .securityMatcher("/**")
                .authorizeHttpRequests(registry -> registry
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(context.getBean(PasswordEncoder.class))
                .and().build();
    }
}
