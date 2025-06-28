package com.alfoli.alfoli.repository;

import com.alfoli.alfoli.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    List<Entrada> findByFechaBetween(LocalDate desde, LocalDate hasta);
}
