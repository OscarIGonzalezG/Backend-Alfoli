package com.alfoli.alfoli.service;

import com.alfoli.alfoli.entity.Necesidad;
import com.alfoli.alfoli.enums.EstadoNecesidad;
import com.alfoli.alfoli.repository.NecesidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alfoli.alfoli.enums.Prioridad;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NecesidadService {

    private final NecesidadRepository necesidadRepository;

    public Necesidad registrarNecesidad(Necesidad necesidad){
        necesidad.setFechaSolicitud(LocalDate.now());
        necesidad.setEstado(necesidad.getEstado() != null ? necesidad.getEstado() : EstadoNecesidad.PENDIENTE);
        return necesidadRepository.save(necesidad);
    }

    public List<Necesidad> obtenerTodas(){
        return necesidadRepository.findAll();
    }

    public Necesidad marcarComoCumplida(Long id){
        Necesidad necesidad = necesidadRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Necesidad no encontrada con ID: " + id));

        necesidad.setEstado(EstadoNecesidad.CUMPLIDA);
        return necesidadRepository.save(necesidad);
    }

    public List<Necesidad> obtenerPendientes(){
        return necesidadRepository.findByEstado(EstadoNecesidad.PENDIENTE);
    }

    public List<Necesidad> obtenerPorPrioridad(Prioridad prioridad) {
        return necesidadRepository.findByPrioridad(prioridad);
    }

    public Necesidad actualizarNecesidad(Long id, Necesidad nuevaData){
        Necesidad necesidad = necesidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Necesidad no encontrada con ID: " + id));

        // Actualizamos solo los campos relevantes
        necesidad.setProductoSolicitado(nuevaData.getProductoSolicitado());
        necesidad.setCantidad(nuevaData.getCantidad());
        necesidad.setPrioridad(nuevaData.getPrioridad());
        necesidad.setEstado(nuevaData.getEstado());
        necesidad.setObservaciones(nuevaData.getObservaciones());

        return necesidadRepository.save(necesidad);
    }

    public void eliminarNecesidad(Long id){
        if (!necesidadRepository.existsById(id)){
            throw  new RuntimeException("No se encontró la necesidad con ID: " + id);
        }
        necesidadRepository.deleteById(id);
    }
}
