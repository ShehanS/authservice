package com.shehan.authservice.authservice.controller;
import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.models.AuthenticationRequest;
import com.shehan.authservice.authservice.models.AuthenticationResponse;
import com.shehan.authservice.authservice.models.LoginUser;
import com.shehan.authservice.authservice.service.LoginUserService;
import com.shehan.authservice.authservice.service.UserService;
import com.shehan.authservice.authservice.models.User;
import com.shehan.authservice.authservice.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
//@RequestMapping("/api")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private LoginUserService loginUserService;
    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody UserDTO userDTO){
        log.debug("Insert {}",userDTO);
        return userService.createUser(userDTO);
    }

    @GetMapping("/all")
    public Flux<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/find")
    public Mono<User> findByUser(@RequestBody AuthenticationRequest login){
        return userService.findByUser(login.getUsername(), login.getPassword());
    }

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthenticationRequest ar) {
            return loginUserService.findByUsername(ar.getUsername()).map((userDetails) -> {
            if (ar.getPassword().equals(userDetails.getPassword())) {
               return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }




}
