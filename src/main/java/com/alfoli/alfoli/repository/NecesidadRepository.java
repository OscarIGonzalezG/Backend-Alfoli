package com.alfoli.alfoli.repository;

import com.alfoli.alfoli.entity.Necesidad;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alfoli.alfoli.enums.EstadoNecesidad;
import com.alfoli.alfoli.enums.Prioridad;
import java.util.List;

public interface NecesidadRepository extends JpaRepository<Necesidad, Long>{

    List<Necesidad> findByEstado(EstadoNecesidad estado);
    List<Necesidad> findByPrioridad(Prioridad prioridad);
}
