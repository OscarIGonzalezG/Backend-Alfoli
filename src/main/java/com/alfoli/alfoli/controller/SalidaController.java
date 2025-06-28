package com.alfoli.alfoli.controller;

import com.alfoli.alfoli.dto.SalidaRequest;
import com.alfoli.alfoli.entity.Salida;
import com.alfoli.alfoli.service.SalidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/salidas")
@RequiredArgsConstructor
public class SalidaController {

    private final SalidaService salidaService;

    @PostMapping
    public ResponseEntity<Salida> registrar(@RequestBody SalidaRequest salidaRequest) {
        Salida salidaRegistrada = salidaService.registrarSalidaDesdeDto(salidaRequest);
        return ResponseEntity.status(201).body(salidaRegistrada);
    }

    @GetMapping
    public ResponseEntity<List<Salida>> listarTodas(){
        List<Salida> salidas = salidaService.obtenerTodasLasSalidas();
        return ResponseEntity.ok(salidas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Salida>> buscarPorFechas(
            @RequestParam("desde") String desdeStr,
            @RequestParam("hasta") String hastaStr) {

        LocalDate desde = LocalDate.parse(desdeStr);
        LocalDate hasta = LocalDate.parse(hastaStr);

        List<Salida> resultado = salidaService.buscarPorFechas(desde, hasta);
        return ResponseEntity.ok(resultado);
    }
}
