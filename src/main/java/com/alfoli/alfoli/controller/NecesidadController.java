package com.alfoli.alfoli.controller;

import com.alfoli.alfoli.entity.Necesidad;
import com.alfoli.alfoli.enums.Prioridad;
import com.alfoli.alfoli.service.NecesidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/necesidades")
@RequiredArgsConstructor
public class NecesidadController {

    private final NecesidadService necesidadService;

    @PostMapping
    public ResponseEntity<Necesidad> registrar(@RequestBody Necesidad necesidad){
       Necesidad nueva = necesidadService.registrarNecesidad(necesidad);
       return ResponseEntity.status(201).body(nueva);
    }

    @GetMapping
    public ResponseEntity<List<Necesidad>> listar() {
        return ResponseEntity.ok(necesidadService.obtenerTodas());
    }

    @PutMapping("/{id}/cumplir")
    public ResponseEntity<Necesidad> marcarCumplida(@PathVariable Long id){
        Necesidad actualizada = necesidadService.marcarComoCumplida(id);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Necesidad>> listarPendientes(){
        return ResponseEntity.ok(necesidadService.obtenerPendientes());
    }

    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<List<Necesidad>> listarPorPrioridad(@PathVariable Prioridad prioridad){
        return ResponseEntity.ok(necesidadService.obtenerPorPrioridad(prioridad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Necesidad> actualizar(@PathVariable Long id, @RequestBody Necesidad nuevaData){
        Necesidad actualizada = necesidadService.actualizarNecesidad(id, nuevaData);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        necesidadService.eliminarNecesidad(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
