package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    public List<Categoria> list();
    Optional<Categoria> getOne(int id);
}
