package com.icunoc.biblioteca.security.entities;

import com.icunoc.biblioteca.models.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Token_Autenticacion")
public class AuthToken {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "ultimoAcceso")
    private LocalDateTime lastAccessTime;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "idUsuario", nullable = false, referencedColumnName = "id")
    private User user;

    public AuthToken() {
        //Constructor vacio de prueba
    }

    @PrePersist
    public void onPrePersist() {
        this.lastAccessTime = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLastAccessTime() {
        return this.lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
