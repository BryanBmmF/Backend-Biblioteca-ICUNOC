package com.icunoc.biblioteca.security.services;
import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.security.entities.AuthToken;
import com.icunoc.biblioteca.security.repositories.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AuthTokenServiceImpl implements AuthTokenService{
    private final AuthTokenRepository repository;

    @Autowired
    public AuthTokenServiceImpl(AuthTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void update(AuthToken token) {
        token.setLastAccessTime(LocalDateTime.now());
        this.repository.save(token);
    }

    @Override
    public void remove(AuthToken authToken) {
        this.repository.delete(authToken);
    }

    @Override
    public boolean isAuthTokenExpired(AuthToken token) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime lastAccessTime = token.getLastAccessTime();

        return Math.abs(ChronoUnit.MINUTES.between(now, lastAccessTime)) > AppConstants.MAX_USER_TOKEN_INACTIVITY_MIN;
    }

    @Override
    public AuthToken createToken(User loggedInUser) {
        AuthToken token = new AuthToken();
        token.setUser(loggedInUser);

        this.repository.saveAndFlush(token);

        return token;
    }

    @Override
    public AuthToken findById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<AuthToken> findByUser(User user) {
        return this.repository.findByUser(user);
    }
}
