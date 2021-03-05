package com.icunoc.biblioteca.repositorio;

import com.icunoc.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
}
