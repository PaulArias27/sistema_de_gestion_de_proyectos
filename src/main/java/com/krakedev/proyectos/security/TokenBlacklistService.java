package com.krakedev.proyectos.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    private final Set<String> blacklist = new HashSet<>();

    public void invalidarToken(String token){

        blacklist.add(token);

    }

    public boolean estaInvalidado(String token){

        return blacklist.contains(token);

    }

}