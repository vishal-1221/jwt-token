package com.security.jwt.controller;

import com.security.jwt.model.JwtRequest;
import com.security.jwt.model.JwtResponse;
import com.security.jwt.model.Users;
import com.security.jwt.security.JWTUtility;
import com.security.jwt.security.MyUserDetails;
import com.security.jwt.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetails userDetails;

    public UserController(UserService userService, JWTUtility jwtUtility, AuthenticationManager authenticationManager, MyUserDetails userDetails) {
        this.userService = userService;
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userDetails = userDetails;
    }

    @GetMapping(value = "/users")
    public List<Users> getUserDetails() {

        return userService.getUserlist();
    }

    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials", e);
        }
        final UserDetails userDetail = userDetails.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetail);
        return new JwtResponse(token);
    }


}
