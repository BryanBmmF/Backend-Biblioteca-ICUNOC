package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.AsignacionLibro;
import com.icunoc.biblioteca.models.Categoria;
import com.icunoc.biblioteca.repositories.AsignacionLibroRepository;
import com.icunoc.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsignacionLibroService {

    @Autowired
    AsignacionLibroRepository repository;

    public List<AsignacionLibro> list(){

        return repository.findAll();
    }

    public List<AsignacionLibro> listByBook(int id) {
        return repository.findAsignacionLibroByIdLibro(id);
    }

    public List<AsignacionLibro> listByCategory(int id) {
        return repository.findAsignacionLibroByIdCategoria(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    public Optional<AsignacionLibro> find(int id){
        return repository.findById(id);
    }

    public void save(AsignacionLibro asignacion){
        repository.save(asignacion);
    }

    public void update(AsignacionLibro asignacion) {
        repository.save(asignacion);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void deleteBookAssignation(int id) {repository.deleteAsignacionLibroByIdLibro(id);}
}
