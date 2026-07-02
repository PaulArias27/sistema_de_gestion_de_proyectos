package com.krakedev.proyectos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

import java.util.List;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + usuario.getRol()
                        )
                )
        );
    }
}