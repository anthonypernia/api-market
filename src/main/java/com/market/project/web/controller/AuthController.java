package com.market.project.web.controller;

import com.market.project.domain.dto.AuthenticationRequest;
import com.market.project.domain.dto.AuthenticationResponse;
import com.market.project.domain.service.PlatziUtilDetailService;
import com.market.project.web.security.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PlatziUtilDetailService platziUtilDetailService;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = platziUtilDetailService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

        }catch(BadCredentialsException ex){
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }





    }


}
