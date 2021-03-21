package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Prestamo;
import com.icunoc.biblioteca.repositories.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    @Autowired
    private PrestamoRepository repository;

    @Override
    public Prestamo listarCodigoReservacion(String codigo) {
        return repository.findByCodigoReservacion(codigo);
    }
    @Autowired
    public PrestamoServiceImpl(PrestamoRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Prestamo> list() {
        return repository.findAll();
    }
    @Override
    public Prestamo add(Prestamo prestamo) {
        //guardamos el prestamo
        return repository.save(prestamo);
    }
    @Override
    public Optional<Prestamo> getOne(int id) {
        return repository.findById(id);
    }
    @Override
    public Optional<Prestamo> getByCodigoReservacion(String codigo) {
        return  repository.findByNombre(codigo);
    }
    @Override
    public void save(Prestamo prestamo) {
        //guardamos el libro
        repository.save(prestamo);
    }
    @Override
    public void update(Prestamo prestamo) {
        //guardamos el usaurio
        repository.save(prestamo);
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
    public boolean existsByCodigoReservacion(String codigo) {
        return  repository.existsByCodigoReservacion(codigo);
    }
}


//PrestamoServiceImpl
