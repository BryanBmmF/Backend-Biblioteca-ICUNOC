package com.icunoc.biblioteca.service;

import com.icunoc.biblioteca.security.entity.Usuario;
import com.icunoc.biblioteca.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByUsuario(String usuario){
        return usuarioRepository.findByUsuario(usuario);
    }

    public boolean existsByUsuario(String usuario){
        return  usuarioRepository.existsByUsuario(usuario);
    }

    public boolean getByRegistroAcademico(String registroAcademico){
        return usuarioRepository.existsByRegistroAcademico(registroAcademico);

    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }



}
