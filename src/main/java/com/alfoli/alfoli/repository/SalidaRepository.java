package com.alfoli.alfoli.repository;

import com.alfoli.alfoli.entity.Salida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalidaRepository extends JpaRepository<Salida, Long> {

    List<Salida> findByFechaBetween(LocalDate desde, LocalDate hasta);
}
