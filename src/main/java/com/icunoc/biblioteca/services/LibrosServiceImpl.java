package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrosServiceImpl implements LibrosService {

    @Autowired
    private LibroRepository repository;

    @Override
    public Libro listarId(int id) {
        return repository.findByIdLibro(id);
    }
}
