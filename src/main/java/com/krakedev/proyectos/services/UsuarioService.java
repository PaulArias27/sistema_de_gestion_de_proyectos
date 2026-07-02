package com.krakedev.proyectos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Optional<Usuario> buscarPorUsername(String username){
        return repository.findByUsername(username);
    }

    public Usuario guardar(Usuario usuario){
        return repository.save(usuario);
    }

}
