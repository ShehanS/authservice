package com.shehan.authservice.authservice.service;

import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.models.SystemUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    public Mono<SystemUser>findByUser(String username, String password);
    public Mono<SystemUser>findByUsername(String username);
    public Mono<SystemUser> createUser(UserDTO userDTO);
    public Flux<SystemUser> findAll();
}
