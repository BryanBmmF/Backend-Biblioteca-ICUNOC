package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibrosServiceImpl implements LibrosService {

    @Autowired
    private LibroRepository repository;

    @Override
    public Libro listarId(int id) {
        return repository.findByIdLibro(id);
    }

    //aqui inicia el crud
    @Autowired
    public LibrosServiceImpl(LibroRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Libro> list(){
        return repository.findAll();
    }

    @Override
    public Libro add(Libro libro) {
        //guardamos el libro
        return repository.save(libro);
    }

    @Override
    public Optional<Libro> getOne(int id){
        return repository.findById(id);
    }

    @Override
    public Optional<Libro> getByNombre(String nombre){
        return  repository.findByNombre(nombre);
    }

    @Override
    public Optional<Libro> getByCodigo(String codigo){
        return  repository.findByCodigo(codigo);
    }

    @Override
    public void save(Libro libro){
        //guardamos el libro
        repository.save(libro);
    }

    @Override
    public void update(Libro libro) {
        //guardamos el libro
        repository.save(libro);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return  repository.existsById(id);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return  repository.existsByNombre(nombre);
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        return repository.existsByCodigo(codigo);
    }
}
