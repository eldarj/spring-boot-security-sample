package com.demo.security.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AbstractUserDetailsAuthenticationProvider authProvider;
    private final RequestMatcher requestMatcher = AnyRequestMatcher.INSTANCE;
    private final RequestMatcher partialForbidden = new OrRequestMatcher(new AntPathRequestMatcher("/api/**"));

    public SecurityConfig(AbstractUserDetailsAuthenticationProvider authProvider) {
        super();
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManager) {
        authManager.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        TokenAuthenticationFilter authFilter = new TokenAuthenticationFilter(partialForbidden);
        authFilter.setAuthenticationManager(authenticationManager());

        http.authenticationProvider(authProvider)
                .addFilterBefore(authFilter, AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(partialForbidden)
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }
}
