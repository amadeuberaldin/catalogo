package com.atividade1.catalogo.controllers;

import com.atividade1.catalogo.models.Filme;
import com.atividade1.catalogo.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    @Autowired
    private FilmeRepository filmeRepository;

    // GET: Listar todos os filmes
    @GetMapping
    public List<Filme> getAllFilmes() {
        return filmeRepository.findAll();
    }

    // GET: Obter detalhes de um filme por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> ResponseEntity.ok().body(filme))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Cadastrar um novo filme
    @PostMapping
    public Filme createFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    // PUT: Atualizar um filme existente
    @PutMapping("/{id}")
    public ResponseEntity<Filme> updateFilme(@PathVariable Long id, @RequestBody Filme filmeDetails) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filme.setTitulo(filmeDetails.getTitulo());
                    filme.setSinopse(filmeDetails.getSinopse());
                    filme.setGenero(filmeDetails.getGenero());
                    filme.setAnoLancamento(filmeDetails.getAnoLancamento());
                    Filme updatedFilme = filmeRepository.save(filme);
                    return ResponseEntity.ok(updatedFilme);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Remover um filme por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(filme -> {
                    filmeRepository.delete(filme);
                    return ResponseEntity.ok().<Void>build();  // Alterado para ResponseEntity<Void>
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
