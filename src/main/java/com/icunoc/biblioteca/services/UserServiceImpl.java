package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.RoleType;
import com.icunoc.biblioteca.model.Role;
import com.icunoc.biblioteca.model.User;
import com.icunoc.biblioteca.repositorio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final String DEFAULT_PASSWORD = "password";

    private final UserRepository repository;

    private final RoleService roleService;

    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoder = encoder;
        this.seed();
    }

    @Override
    public void seed() {
        if (this.repository.count() > 0) {
            return;
        }

        final String defaultPassword = this.encoder.encode(DEFAULT_PASSWORD);
        final Role adminRole = this.roleService.find(RoleType.ROLE_ADMIN);
        final Role userRole = this.roleService.find(RoleType.ROLE_USER);

        User admin = new User();
        admin.setPassword(defaultPassword);
        admin.setUsername("admin");
        admin.setAuthorities(List.of(adminRole, userRole));

        this.repository.save(admin);

        User user = new User();
        user.setPassword(defaultPassword);
        user.setUsername("user");
        user.setAuthorities(List.of(userRole));

        this.repository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.repository.findUserByUsername(username);

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("Could not find user with username " + username);
    }
}
