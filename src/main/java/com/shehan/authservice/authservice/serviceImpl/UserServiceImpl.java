package com.shehan.authservice.authservice.serviceImpl;

import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.service.UserService;
import com.shehan.authservice.authservice.models.SystemUser;
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
    public Mono<SystemUser> findByUser(String username, String password) {
        return userRepository.findByUser(username,password);
    }

    @Override
    public Mono<SystemUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<SystemUser> createUser(UserDTO userDTO) {
       SystemUser user = new SystemUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
       return userRepository.insert(user);
    }

    @Override
    public Flux<SystemUser> findAll() {
        return userRepository.findAll();
    }
}
