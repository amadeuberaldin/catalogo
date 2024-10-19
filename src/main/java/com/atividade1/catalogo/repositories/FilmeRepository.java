package com.atividade1.catalogo.repositories;

import com.atividade1.catalogo.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
