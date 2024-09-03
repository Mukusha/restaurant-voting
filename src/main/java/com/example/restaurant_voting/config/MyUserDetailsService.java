package com.example.restaurant_voting.config;

import com.example.restaurant_voting.model.User;
import com.example.restaurant_voting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating '{}'" + username);
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(username);
        return new AuthUser(optionalUser.orElseThrow(
                () -> new UsernameNotFoundException("User '" + username + "' was not found")));
    }
}
