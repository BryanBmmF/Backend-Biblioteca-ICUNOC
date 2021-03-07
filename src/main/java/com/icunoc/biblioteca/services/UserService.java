package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void seed();
    List<User> list();
    User add(User user);
    Optional<User> getOne(long id);
    Optional<User> getByNombre(String nombre);
    Optional<User> getByUsername(String username);
    void save(User user);
    void update(User user);
    void delete(long id);
    boolean existsById(long id);
    boolean existsByNombre(String nombre);
    boolean existsByUsername(String username);

}
