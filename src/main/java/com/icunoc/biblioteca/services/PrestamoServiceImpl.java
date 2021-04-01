package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Libro;
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

    @Override
    public void save(Prestamo prestamo) {
        //guardamos el libro
        repository.save(prestamo);
    }
    @Override
    public List<Prestamo> list(String estado) {
        return repository.findByEstado(estado);
    }

    @Override
    public Prestamo getOne(String id){

        return repository.findByCodigoReservacion(id);
    }

    public void setRepository(PrestamoRepository prestamoRepository) {
        this.repository = prestamoRepository;
    }
}
