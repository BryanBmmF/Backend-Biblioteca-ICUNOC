package com.icunoc.biblioteca.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//Clase que implementa los privilegios de cada usuario
public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String usuario;
    private String password;
    private String registroAcademico;
    //en lugar de roles, autorizaciones
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String usuario, String password, String registroAcademico, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.registroAcademico = registroAcademico;
        this.authorities = authorities;
    }

    /**
     * Metodo statico build que asigna los privilegios a cada usuario
     * */
    public static UsuarioPrincipal build(Usuario usuario){
        //convirtiendo los roles en authorities
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        //retornando un nuevo usuario principal
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getUsuario(),usuario.getPassword(),
                usuario.getRegistroAcademico(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }
}
