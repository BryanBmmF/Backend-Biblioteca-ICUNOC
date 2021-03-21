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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final String DEFAULT_ADMIN = "admin";
    private final String DEFAULT_USER = "user";
    private final String DEFAULT_PASSWORD = "password";
    private final String DEFAULT_REGSITRO_1 = "111111111";
    private final String DEFAULT_REGISTRO_2 = "222222222";
    private final String DEFAULT_TIPO_ADMINISTRADOR = "Administrador";
    private final String DEFAULT_TIPO_BIBLIOTECARIO = "Bibliotecario";

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
        admin.setNombre(DEFAULT_ADMIN);
        admin.setNumeroRegistro(DEFAULT_REGSITRO_1);
        admin.setTipo(DEFAULT_TIPO_ADMINISTRADOR);
        admin.setAuthorities(List.of(adminRole, userRole));

        //guardando usuario administrador
        this.repository.save(admin);

        User user = new User();
        user.setPassword(defaultPassword);
        user.setUsername(DEFAULT_USER);
        user.setNombre(DEFAULT_USER);
        user.setNumeroRegistro(DEFAULT_REGISTRO_2);
        user.setTipo(DEFAULT_TIPO_BIBLIOTECARIO);
        user.setAuthorities(List.of(userRole));

        //guardando usuario normal
        this.repository.saveAndFlush(user);
    }

    /**Metodos para el crud de usaurios
     * listar() : devuelve una lista de usaurios registrados
     * getOne(): devuelve un unico usuario por id
     * getByNombre(): devuelve un unico usuario por nombre
     * save(): guarda un nuevo usuario
     * delete(): elimina un usuario segun su id
     * existsById(): evalua si existe un usuario segun su id
     * existsByNombre(): evalua si existe un usuario segun su nombre
     * */
    @Override
    public List<User> list(){
        return repository.findAll();
    }

    @Override
    public Optional<User> getOne(long id){
        return repository.findById(id);
    }

    @Override
    public Optional<User> getByNombre(String nombre){
        return  repository.findByNombre(nombre);
    }

    @Override
    public Optional<User> getByUsername(String username){
        return  repository.findByUsername(username);
    }

    @Override
    public void save(User user){
        //verificamos el tipo de usuario
        if (user.getTipo().equals(DEFAULT_TIPO_ADMINISTRADOR)){
            user.setAuthorities(List.of(this.roleService.find(RoleType.ROLE_ADMIN),this.roleService.find(RoleType.ROLE_USER)));
        }
        if (user.getTipo().equals(DEFAULT_TIPO_BIBLIOTECARIO)){
            user.setAuthorities(List.of(this.roleService.find(RoleType.ROLE_USER)));
        }
        //seteamos el pass encripatado
        user.setPassword(this.encoder.encode(user.getPassword()));
        repository.save(user);
    }
    @Override
    public void update(User user){
        //seteamos el pass encripatado, las autorizaciones no
        user.setPassword(this.encoder.encode(user.getPassword()));
        //guardamos el usaurio
        repository.save(user);
    }

    @Override
    public void delete(long id){
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(long id){
        return  repository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre){
        return  repository.existsByNombre(nombre);
    }

    @Override
    public boolean existsByUsername(String username){
        return  repository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.repository.findUserByUsername(username);

        if (user != null) {
            return user;
        }

        throw new UsernameNotFoundException("Could not find user with username " + username);
    }

    /*json para crear user
    *
    * {"nombre": "admin3", "numeroRegistro": "333333333", "username": "admin3", "password": "password"}
    * */
}
