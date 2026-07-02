package com.krakedev.proyectos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setRol("ADMIN");

                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("user").isEmpty()) {

                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setRol("USER");

                usuarioRepository.save(user);
            }

        };
    }
}
