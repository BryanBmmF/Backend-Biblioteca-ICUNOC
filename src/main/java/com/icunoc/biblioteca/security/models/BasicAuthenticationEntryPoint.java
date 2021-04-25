package com.icunoc.biblioteca.security.models;

import com.google.gson.Gson;
import com.icunoc.biblioteca.constants.AppConstants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private Map<String, Object> MY_MAP = new HashMap<String, Object>();
    private Gson gson;

    public BasicAuthenticationEntryPoint(Gson gson) {
        this.gson = gson;
    }

    public BasicAuthenticationEntryPoint() {
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Content-Type", "application/json");

        response.addHeader("Access-Control-Allow-Headers", "session");
        response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,PUT,POST,DELETE,PATCH");
        response.addHeader("Access-Control-Allow-Origin", "*");

        response.getWriter().println(this.gson.toJson(
            (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, "Invalid Credentials"))
        );
    }

}
