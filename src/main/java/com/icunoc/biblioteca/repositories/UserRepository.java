package com.icunoc.biblioteca.repositories;

import com.icunoc.biblioteca.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
}
