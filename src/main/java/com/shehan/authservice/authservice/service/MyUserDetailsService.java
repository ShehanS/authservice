package com.shehan.authservice.authservice.service;

import com.shehan.authservice.authservice.models.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        getUser(username);

        return new User("foo", "foo", new ArrayList<>());
    }

    Mono<com.shehan.authservice.authservice.models.User> getUser(String username){
        return userService.findByUsername(username);
    }
}
