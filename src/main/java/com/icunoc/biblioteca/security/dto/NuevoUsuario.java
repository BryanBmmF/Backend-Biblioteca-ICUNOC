package com.icunoc.biblioteca.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String nombreUsuario;
    @Email
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}


    //{"usuario": "admin", "password": "admin", "nombre": "nombre_admin", "registroAcademico": "201721211", "roles": ["admin"]}
    // {"nombre": "admin","nombreUsuario": "admin", "email": "admin@coco.com", "password": "admin", "roles": ["admin"]}
    //{"nombre": "user1","nombreUsuario": "user1", "email": "user1@coco.com", "password": "user1"}

    //{"nombreUsuario": "user1", "password": "user1"}
