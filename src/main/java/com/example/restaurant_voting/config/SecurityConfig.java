package com.example.restaurant_voting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  /*  @Bean //возвращаем кастомный MyUserDetailsService, который напишем далее
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService();
    }*/
    @Bean //Ставим степень кодировки, с которой кодировали пароль в базе
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5);
    }

    public AuthenticationProvider authenticationProvider(){
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

 /*   @Bean
    public UserDetailsManager users(DataSource dataSource) {
        UserDetails user = AuthUser.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);

        return user;
    }*/

}
