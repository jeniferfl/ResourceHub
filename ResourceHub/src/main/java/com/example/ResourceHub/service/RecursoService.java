package com.example.ResourceHub.service;

import com.example.ResourceHub.model.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ResourceHub.repository.RecursoRepository;
import java.util.List;
import java.util.Optional;


@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> obtenerTodos(){
        return recursoRepository.findAll();
    }

    public Optional<Recurso> obtenerPorId(Long id){
        return recursoRepository.findById(id);
    }

    public Recurso crear(Recurso recurso){
        return recursoRepository.save(recurso);
    }
    public boolean eliminarRecurso(Long id) {
        return recursoRepository.deleteById(id);
    }

}
