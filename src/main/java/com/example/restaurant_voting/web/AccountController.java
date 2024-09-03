package com.example.restaurant_voting.web;

import com.example.restaurant_voting.config.AuthUser;
import com.example.restaurant_voting.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthUser authUser) {
        System.out.println("------ ! Вошел пользователь "+authUser.getUser().getEmail());
        return authUser.getUser();
    }
}
