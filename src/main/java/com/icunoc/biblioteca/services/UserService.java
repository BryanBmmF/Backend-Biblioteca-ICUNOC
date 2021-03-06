package com.icunoc.biblioteca.services;

import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {
    void seed();
}
