package com.demo.security.auth.service;

public interface AuthenticationService<T> {
    T authenticate(String... var);
}
