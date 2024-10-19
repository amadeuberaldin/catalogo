package com.atividade1.catalogo.controllers;

import com.atividade1.catalogo.models.Analise;
import com.atividade1.catalogo.repositories.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseRepository analiseRepository;

    // GET: Listar todas as análises
    @GetMapping
    public List<Analise> getAllAnalises() {
        return analiseRepository.findAll();
    }

    // GET: Obter detalhes de uma análise por ID
    @GetMapping("/{id}")
    public ResponseEntity<Analise> getAnaliseById(@PathVariable Long id) {
        return analiseRepository.findById(id)
                .map(analise -> ResponseEntity.ok().body(analise))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Cadastrar uma nova análise
    @PostMapping
    public Analise createAnalise(@RequestBody Analise analise) {
        return analiseRepository.save(analise);
    }

    // PUT: Atualizar uma análise existente
    @PutMapping("/{id}")
    public ResponseEntity<Analise> updateAnalise(@PathVariable Long id, @RequestBody Analise analiseDetails) {
        return analiseRepository.findById(id)
                .map(analise -> {
                    analise.setTextoAnalise(analiseDetails.getTextoAnalise());
                    analise.setNota(analiseDetails.getNota());
                    Analise updatedAnalise = analiseRepository.save(analise);
                    return ResponseEntity.ok(updatedAnalise);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Remover uma análise por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalise(@PathVariable Long id) {
        return analiseRepository.findById(id)
                .map(analise -> {
                    analiseRepository.delete(analise);
                    return ResponseEntity.ok().<Void>build();  // Alterado para ResponseEntity<Void>
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
