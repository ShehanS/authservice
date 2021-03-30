package com.shehan.authservice.authservice.serviceImpl;

import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.models.LoginUser;
import com.shehan.authservice.authservice.service.UserService;
import com.shehan.authservice.authservice.models.User;
import com.shehan.authservice.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Mono<LoginUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<LoginUser> createUser(LoginUser loginUser) {
        return userRepository.save(loginUser);
    }
}
