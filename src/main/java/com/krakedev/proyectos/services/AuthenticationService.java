package com.krakedev.proyectos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.dto.LoginRequest;
import com.krakedev.proyectos.dto.LoginResponse;
import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;
import com.krakedev.proyectos.security.JwtService;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())

        );

        Usuario usuario = usuarioRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generarToken(
                usuario.getUsername(),
                usuario.getRol());

        return new LoginResponse(token,usuario.getRol());

    }

}
