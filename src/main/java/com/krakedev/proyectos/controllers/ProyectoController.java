package com.krakedev.proyectos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.services.ProyectoService;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowedHeaders = {"Authorization", "Content-Type"},
	    methods = {
	        RequestMethod.GET,
	        RequestMethod.POST,
	        RequestMethod.PUT,
	        RequestMethod.DELETE
	    }
	)
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Proyecto proyecto) {

        try {

            Proyecto nuevoProyecto = proyectoService.guardar(proyecto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoProyecto);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        try {

            return ResponseEntity.ok(proyectoService.listar());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {

        try {

            Optional<Proyecto> proyecto = proyectoService.buscarPorId(id);

            if (proyecto.isPresent()) {
                return ResponseEntity.ok(proyecto.get());
            }

            return ResponseEntity.notFound().build();

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer id,
            @RequestBody Proyecto proyecto) {

        try {

            proyecto.setId(id);

            return ResponseEntity.ok(
                    proyectoService.guardar(proyecto));

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        try {

            proyectoService.eliminar(id);

            return ResponseEntity.ok("Proyecto eliminado correctamente");

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/publico/resumen")
    public ResponseEntity<Long> resumen() {
        return ResponseEntity.ok(proyectoService.contar());
    }
}