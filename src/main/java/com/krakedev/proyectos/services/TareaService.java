package com.krakedev.proyectos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.repositories.TareaRepository;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea guardar(Tarea tarea) {

        String prioridad = tarea.getPrioridad();

        if (prioridad == null ||
            !(prioridad.equals("ALTA") ||
              prioridad.equals("MEDIA") ||
              prioridad.equals("BAJA"))) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "{\"error\": \"Prioridad no válida\"}"
            );
        }

        return tareaRepository.save(tarea);
    }

    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    public Optional<Tarea> buscarPorId(Integer id) {
        return tareaRepository.findById(id);
    }

    public Tarea actualizar(Integer id, Tarea tarea) {
        tarea.setId(id);
        return tareaRepository.save(tarea);
    }

    public void eliminar(Integer id) {
        tareaRepository.deleteById(id);
    }
}
