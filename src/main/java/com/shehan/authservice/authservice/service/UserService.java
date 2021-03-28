package com.shehan.authservice.authservice.service;

import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.models.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<User>findByUser(String username, String password);
    public Mono<User>findByUsername(String username);
    public Mono<User> createUser(UserDTO userDTO);
    public Flux<User> findAll();
}
