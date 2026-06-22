package com.krakedev.proyectos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.services.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Empleado empleado) {

        try {

            Empleado nuevoEmpleado = empleadoService.guardar(empleado);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoEmpleado);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        try {

            return ResponseEntity.ok(empleadoService.listar());

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {

        try {

            Optional<Empleado> empleado = empleadoService.buscarPorId(id);

            if (empleado.isPresent()) {
                return ResponseEntity.ok(empleado.get());
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
            @RequestBody Empleado empleado) {

        try {

            empleado.setId(id);

            return ResponseEntity.ok(
                    empleadoService.guardar(empleado));

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {

        try {

            empleadoService.eliminar(id);

            return ResponseEntity.ok("Empleado eliminado correctamente");

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
