package com.shehan.authservice.authservice.service;

import com.shehan.authservice.authservice.models.LoginUser;
import com.shehan.authservice.authservice.models.User;
import com.shehan.authservice.authservice.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUserService {
    @Autowired
    private UserService userService;
    private Map<String, LoginUser> data;
    @PostConstruct
    public void init(){
        data = new HashMap<>();

        //username:passwowrd -> user:user
        data.put("user", new LoginUser("user", "123", true, Arrays.asList(Role.ROLE_USER)));

        //username:passwowrd -> admin:admin
        data.put("admin", new LoginUser("admin", "123", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<LoginUser> findByUsername(String username) {
        if (data.containsKey(username)) {
            return Mono.just(data.get(username));
        } else {
            return Mono.empty();
        }
    }

}
