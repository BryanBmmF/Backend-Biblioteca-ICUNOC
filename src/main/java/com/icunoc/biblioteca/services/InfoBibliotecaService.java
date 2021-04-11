package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.InfoBiblioteca;

import java.util.Optional;

public interface InfoBibliotecaService {
    Optional<InfoBiblioteca> getOne(long id);
    void update(InfoBiblioteca datos);
    boolean existsById(long id);
}
