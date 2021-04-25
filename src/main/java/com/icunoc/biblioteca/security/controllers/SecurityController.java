package com.icunoc.biblioteca.security.controllers;

import com.icunoc.biblioteca.constants.AppConstants;
import com.icunoc.biblioteca.scheduleds.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class SecurityController {

    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    private Map<String, Object> MY_MAP = new HashMap<String, Object>();

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter springSecurityFilterChain;

    @PostConstruct
    public void inPostConstruct() {
        FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
        List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
        list.stream()
                .flatMap(chain -> chain.getFilters().stream())
                .forEach(filter -> logger.info(filter.getClass() + " "));
    }

    @PostMapping("/authorize")
    @PreAuthorize("isFullyAuthenticated()")
    public Map<String, Object> authorizeAction(HttpSession session) {
        return (Map<String, Object>) MY_MAP.put(AppConstants.AUTH_TOKEN_NAME, session.getAttribute(AppConstants.AUTH_TOKEN_NAME));
    }

    @GetMapping("/anon")
    @PreAuthorize("isAnonymous()")
    public Map<String, Object> anonAction() {
        return (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, "You are anon!");
    }

    @GetMapping("/logged")
    @PreAuthorize("isFullyAuthenticated()")
    public Map<String, Object> loggedAction(Principal principal) {
        return (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, principal.getName() + ", you are logged!");

    }

    //valida permiso de administrador
    @GetMapping("/permissionAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Map<String, Object> adminAction(Principal principal) {
        return (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, principal.getName() + ", you are admin!");
    }

    //valida permiso de usuario
    @GetMapping("/permissionUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Map<String, Object> userAction(Principal principal) {
        return (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, principal.getName() + ", you are user!");
    }

    //En caso de no permitir autorizacion
    @ExceptionHandler({AccessDeniedException.class})
    public Map<String, Object> accessDeniedHandlerAction(HttpServletResponse response) {
        return (Map<String, Object>) MY_MAP.put(AppConstants.RESPONSE_BODY_MESSAGE_KEY, AppConstants.NOT_AUTHORIZE);
    }

    public void setMY_MAP(Map<String, Object> MY_MAP){
        this.MY_MAP=MY_MAP;
    }
}
