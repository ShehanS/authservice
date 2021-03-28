package com.shehan.authservice.authservice.controller;
import com.shehan.authservice.authservice.dto.UserDTO;
import com.shehan.authservice.authservice.models.AuthenticationRequest;
import com.shehan.authservice.authservice.models.AuthenticationResponse;
import com.shehan.authservice.authservice.service.MyUserDetailsService;
import com.shehan.authservice.authservice.service.UserService;
import com.shehan.authservice.authservice.models.User;
import com.shehan.authservice.authservice.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api")
public class UserController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
           authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
