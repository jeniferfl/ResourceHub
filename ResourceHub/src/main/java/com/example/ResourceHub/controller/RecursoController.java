package com.example.ResourceHub.controller;

import com.example.ResourceHub.model.Recurso;
import com.example.ResourceHub.repository.RecursoRepository;
import com.example.ResourceHub.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public List<Recurso> findAll() {
        return recursoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Recurso listarPorId(@PathVariable Long id) {
        return recursoService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Recurso crear(@RequestBody Recurso recurso) {
        return recursoService.crear(recurso);
    }

    @PatchMapping("/{id}")
    public Optional<Recurso> actualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Recurso recurso) {

        return recursoRepository.findById(id).map(recursoExist -> {

            if (recurso.getTitulo() != null
                    && !recurso.getTitulo().isEmpty()) {
                recursoExist.setTitulo(recurso.getTitulo());
            }

            if (recurso.getUrl() != null
                    && !recurso.getUrl().isEmpty()) {
                recursoExist.setUrl(recurso.getUrl());
            }

            if (recurso.getCategoria() != null
                    && !recurso.getCategoria().isEmpty()) {
                recursoExist.setCategoria(recurso.getCategoria());
            }

            if (recurso.getTipo() != null
                    && !recurso.getTipo().isEmpty()) {
                recursoExist.setTipo(recurso.getTipo());
            }

            return recursoRepository.save(recursoExist);
        });
    }

    @PutMapping("/{id}")
    public Optional<Recurso> actualizar(Long id, Recurso recurso) {
        return recursoRepository.findById(id).map(existente -> {
            existente.setTitulo(recurso.getTitulo());
            existente.setUrl(recurso.getUrl());
            existente.setCategoria(recurso.getCategoria());
            existente.setTipo(recurso.getTipo());

            return recursoRepository.save(existente);
        });
    }

    @DeleteMapping("/{id}")
    public boolean eliminarRecurso(@PathVariable Long id) {
        return recursoService.eliminarRecurso(id);
    }
}