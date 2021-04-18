package com.market.project.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlatziUtilDetailService implements UserDetailsService {


    // noop hace que se acepte la contraseña sin estar encriptada
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("admin", "{noop}admin", new ArrayList<>());
    }
}
