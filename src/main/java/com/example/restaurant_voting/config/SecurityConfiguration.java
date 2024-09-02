package com.example.restaurant_voting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration { //extends WebSecurityConfigurerAdapter

    /*  @Autowired
      public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
          auth.inMemoryAuthentication()
                  .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                  .withUser("user@gmail.com").password("{noop}password").roles("USER").and()
                  .withUser("admin@javaops.ru").password("{noop}admin").roles("USER", "ADMIN");
      }*/
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //{noop} - не следует использовать кодировку
        UserDetails user = User.builder()
                .username("user@gmail.com")
                .password("{noop}password")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin@javaops.ru")
                .password("{noop}admin")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
