package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.enums.RoleType;
import com.icunoc.biblioteca.models.Role;

import java.util.List;
public interface RoleService{
    void initialize();

    Role find(RoleType roleType);

    List<Role> findAll();
}
