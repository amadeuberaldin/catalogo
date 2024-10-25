package com.atividade1.catalogo.controllers;

import com.atividade1.catalogo.models.Analise;
import com.atividade1.catalogo.models.Filme;
import com.atividade1.catalogo.repositories.FilmeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public String listarFilmes(Model model) {
        // Busca os filmes no banco de dados em vez de usar uma lista em memória
        model.addAttribute("filmes", filmeRepository.findAll());
        return "listaFilmes";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "formFilme";
    }

    @PostMapping
    public String cadastrarFilme(Filme filme) {
        // Salva o filme no banco de dados
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme != null) {
            model.addAttribute("filme", filme);
            model.addAttribute("analise", new Analise());
            return "detalhesFilme";
        } else {
            return "redirect:/filmes"; // Redireciona se o filme não for encontrado
        }
    }

    @PostMapping("/{id}/analise")
    public String adicionarAnalise(@PathVariable Long id, Analise analise) {
        Filme filme = filmeRepository.findById(id).orElse(null);
        if (filme != null) {
            filme.getAnalises().add(analise);  // Adiciona a análise ao filme
            filmeRepository.save(filme);  // Atualiza o filme no banco de dados
        }
        return "redirect:/filmes/" + id;
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);
        if (filmeOpt.isPresent()) {
            model.addAttribute("filme", filmeOpt.get());
            return "editarFilme";
        } else {
            return "redirect:/filmes";
        }
    }
}
