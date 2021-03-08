package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.models.User;
import com.icunoc.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    public List<Libro> list(){
        return libroRepository.findAll();
    }

    public Optional<Libro> getOne(int id){
        return libroRepository.findById(id);
    }

    public Optional<Libro> getByNombre(String nombre){
        return libroRepository.findByNombre(nombre);
    }

    public void save(Libro libro){
        libroRepository.save(libro);
    }

    public void delete(int id){
        libroRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return libroRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return libroRepository.existsByNombre(nombre);
    }
}
