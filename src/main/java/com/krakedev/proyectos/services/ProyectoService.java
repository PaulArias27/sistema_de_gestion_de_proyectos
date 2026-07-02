package com.krakedev.proyectos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.ProyectoRepository;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> buscarPorId(Integer id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto actualizar(Integer id, Proyecto proyecto) {
        proyecto.setId(id);
        return proyectoRepository.save(proyecto);
    }

    public void eliminar(Integer id) {
        proyectoRepository.deleteById(id);
    }
    
    public Long contar() {
        return proyectoRepository.count();
    }
}
