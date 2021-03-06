package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.RoleType;
import com.icunoc.biblioteca.models.Role;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final String DEFAULT_ADMIN = "admin";
    private final String DEFAULT_USER = "user";
    private final String DEFAULT_PASSWORD = "password";
    private final String DEFAULT_REGSITRO_1 = "111111111";
    private final String DEFAULT_REGISTRO_2 = "222222222";

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

        //codificacion del password, y declaracion de los roles por defecto
        final String defaultPassword = this.encoder.encode(DEFAULT_PASSWORD);
        final Role adminRole = this.roleService.find(RoleType.ROLE_ADMIN);
        final Role userRole = this.roleService.find(RoleType.ROLE_USER);

        /*Creacion de Usuarios por defecto, uno administrador y uno usuario comun*/
        User admin = new User();
        admin.setPassword(defaultPassword);
        admin.setUsername(DEFAULT_ADMIN);
        admin.setNumeroRegistro(DEFAULT_REGSITRO_1);
        admin.setAuthorities(List.of(adminRole, userRole));

        //guardando usuario administrador
        this.repository.save(admin);

        User user = new User();
        user.setPassword(defaultPassword);
        user.setUsername(DEFAULT_USER);
        user.setNumeroRegistro(DEFAULT_REGISTRO_2);
        user.setAuthorities(List.of(userRole));

        //guardando usuario normal
        this.repository.saveAndFlush(user);
    }

    @Override
    public User add(User user) {
        //por defecto se van a registrar solo usuarios administradores
        //seteamos el pass encripatado y las autorizaciones
        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setAuthorities(List.of(this.roleService.find(RoleType.ROLE_ADMIN),this.roleService.find(RoleType.ROLE_USER)));
        //guardamos el usaurio
        return repository.save(user);
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
