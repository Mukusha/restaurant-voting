package com.example.restaurant_voting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean //Ставим степень кодировки, с которой кодировали пароль в базе. По умолчанию 10
    public PasswordEncoder passwordEncoder() {
        //   System.out.println("Пароль   "+new BCryptPasswordEncoder().encode("admin")+"   !!!");
        return new BCryptPasswordEncoder();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(new MyUserDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  //вход без авторизации
                        // .requestMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() //с авторизацией и аутентификацией
                )
                .formLogin((form) -> form
                        //.loginPage("/login")
                        .failureUrl("/login?error=true")
                        .defaultSuccessUrl("/api/account", true)
                        .permitAll()
                ).logout((logout) -> logout
                                //      .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                        // .deleteCookies("JSESSIONID")
                        //.invalidateHttpSession(true)
                        //.clearAuthentication(true)
                )
                .build();
    }
}
