package com.icunoc.biblioteca.security.services;

import com.icunoc.biblioteca.model.User;
import com.icunoc.biblioteca.security.entities.AuthToken;

import java.util.List;
public interface AuthTokenService {
    void update(AuthToken token);

    void remove(AuthToken authToken);

    boolean isAuthTokenExpired(AuthToken token);

    AuthToken createToken(User loggedInUser);

    AuthToken findById(String id);

    List<AuthToken> findByUser(User user);
}
