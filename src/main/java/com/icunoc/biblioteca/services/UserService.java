package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void seed();
    //List<User> listar();
    //User listar(long id);
    User add(User user);
    //User edit(User user);
    //User delete(long id);
}
