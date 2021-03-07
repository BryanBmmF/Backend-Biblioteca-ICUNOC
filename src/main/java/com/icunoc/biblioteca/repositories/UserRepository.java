package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
    //metodo extra para buscar usaurios por nombre
    Optional<User> findByNombre(String nombre);
    Optional<User> findByUsername(String username);
    //metodo extra para saber si un usuario existe
    boolean existsByNombre(String nombre);
    boolean existsByUsername(String username);

}
