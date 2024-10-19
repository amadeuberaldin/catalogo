package com.atividade1.catalogo.repositories;

import com.atividade1.catalogo.models.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long> {
}