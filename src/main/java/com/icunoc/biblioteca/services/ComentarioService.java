package com.icunoc.biblioteca.services;

import com.icunoc.biblioteca.models.Comentario;
import com.icunoc.biblioteca.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    public List<Comentario> listByBook(String idLibro) {
        return comentarioRepository.findByIdLibro(idLibro);
    }

    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void setComentarioRepository(ComentarioRepository repository) {
        this.comentarioRepository = repository;
    }
}
