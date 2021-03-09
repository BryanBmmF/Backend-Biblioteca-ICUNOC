package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.repositories.CategoriaRepository;
import com.icunoc.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> list(){

        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getOne(int id){
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> getByNombre(String nombre){
        return categoriaRepository.findByNombre(nombre);
    }

    public boolean existsById(int id){
        return categoriaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return categoriaRepository.existsByNombre(nombre);
    }
}
