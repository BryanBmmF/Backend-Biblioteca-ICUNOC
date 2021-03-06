package com.icunoc.biblioteca.security.filters;
import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.security.entities.AuthToken;
import com.icunoc.biblioteca.security.services.AuthTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenAuthenticationFilter implements Filter {
    private final AuthTokenService authTokenService;

    public TokenAuthenticationFilter(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        final String accessToken = httpRequest.getHeader(AppConstants.AUTH_TOKEN_NAME);

        if (accessToken != null) {
            AuthToken token = this.authTokenService.findById(accessToken);

            if (token != null) {
                if (this.authTokenService.isAuthTokenExpired(token)) {
                    this.authTokenService.remove(token);
                } else {
                    final User user = token.getUser();
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    this.authTokenService.update(token);
                }
            }
            //TODO you can set to unauthorized if that is what you need.
        }

        chain.doFilter(request, response);
    }
}
