package com.demo.security.auth.config;

import com.demo.security.auth.model.SuperUser;
import com.demo.security.auth.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final AuthenticationService<SuperUser> authenticationService;

    public TokenAuthenticationProvider(AuthenticationService<SuperUser> authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        // additional checks
    }

    @Override
    protected UserDetails retrieveUser(
            String username,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String token = String.valueOf(usernamePasswordAuthenticationToken.getCredentials());
        SuperUser superUser = authenticationService.authenticate(token);

        return new User(superUser.getName(), "", AuthorityUtils.createAuthorityList("USER"));
    }
}
