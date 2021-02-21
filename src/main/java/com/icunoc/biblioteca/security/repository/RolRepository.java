package com.icunoc.biblioteca.security.repository;

import com.icunoc.biblioteca.security.entity.Rol;
import com.icunoc.biblioteca.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
