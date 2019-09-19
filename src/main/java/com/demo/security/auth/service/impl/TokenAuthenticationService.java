package com.demo.security.auth.service.impl;

import com.demo.security.auth.model.SuperUser;
import com.demo.security.auth.service.AuthenticationService;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationService implements AuthenticationService<SuperUser> {
    @Override
    public SuperUser authenticate(String... token) {
        // get list of http cookies from a request i.e. the wanted cookie
        // check and authenticate the auth token with the 3rd party service

        // if response ok -> return dummy user obj (in production a User/UserDetails)
        return new SuperUser("Eldar Jahijagic");

        // else
        // non-authenticated
    }
}
