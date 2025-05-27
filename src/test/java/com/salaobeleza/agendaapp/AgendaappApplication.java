package com.salaobeleza.agendaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@SpringBootApplication
@EnableWebSecurity
public class AgendaappApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaappApplication.class, args);
    }

    // ✅ Desativa CSRF diretamente aqui
    @Bean
    public SecurityFilterChain filterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ❌ CSRF desativado
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // ✅ Libera todos os endpoints
            .sessionManagement(sess -> sess.sessionCreationPolicy(STATELESS));
        return http.build();
    }
}
