package com.krakedev.proyectos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.dto.LoginRequest;
import com.krakedev.proyectos.security.TokenBlacklistService;
import com.krakedev.proyectos.services.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @Autowired
    private TokenBlacklistService blacklist;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request){

        return ResponseEntity.ok(
                service.login(request));

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader("Authorization")
            String authorization){

        String token = authorization.substring(7);

        blacklist.invalidarToken(token);

        return ResponseEntity.ok("Logout correcto");

    }

}