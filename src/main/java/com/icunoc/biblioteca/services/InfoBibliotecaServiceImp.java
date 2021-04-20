package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.InfoBiblioteca;
import com.icunoc.biblioteca.repositories.InfoBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoBibliotecaServiceImp implements InfoBibliotecaService{

    @Autowired
    private InfoBibliotecaRepository repository;

    @Override
    public Optional<InfoBiblioteca> getOne(long id) {
        return  repository.findById(id);
    }

    @Override
    public void update(InfoBiblioteca datos) {
        repository.save(datos);
    }

    @Override
    public boolean existsById(long id) {
        return  repository.existsById(id);
    }

    public void setRepositoryMock(InfoBibliotecaRepository bibliotecaRepository){
        this.repository = bibliotecaRepository;
    }
}
