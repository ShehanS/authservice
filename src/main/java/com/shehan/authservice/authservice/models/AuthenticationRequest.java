package com.shehan.authservice.authservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AuthenticationRequest {
    private String username;
    private String password;

}
