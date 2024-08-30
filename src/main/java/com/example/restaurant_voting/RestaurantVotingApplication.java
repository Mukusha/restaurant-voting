package com.example.restaurant_voting;

import com.example.restaurant_voting.model.Role;
import com.example.restaurant_voting.model.User;
import com.example.restaurant_voting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {
	private final UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(RestaurantVotingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		userRepository.save(new User("user@gmail.com","User_1","LastName","111", Collections.singleton(Role.ROLE_USER)));
		userRepository.save(new User("admin@gmail.com","Admin_1","LastName","111", Collections.singleton(Role.ROLE_ADMIN)));
		System.out.println(userRepository.findAll());
	}
}
