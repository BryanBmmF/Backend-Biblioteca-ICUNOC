package com.icunoc.biblioteca.security.repositories;

import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.security.entities.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, String>{
    List<AuthToken> findByUser(User user);
}
