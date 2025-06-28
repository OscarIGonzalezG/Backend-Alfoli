package com.alfoli.alfoli.controller;

import com.alfoli.alfoli.dto.EntradaRequest;
import com.alfoli.alfoli.entity.Entrada;
import com.alfoli.alfoli.service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @PostMapping
    public ResponseEntity<Entrada> registrar(@RequestBody EntradaRequest entradaRequest){
        Entrada entradaRegistrada = entradaService.registrarEntradaDesdeDto(entradaRequest);
        return ResponseEntity.status(201).body(entradaRegistrada);
    }

    @GetMapping
    public ResponseEntity<List<Entrada>> listarTodas(){
        List<Entrada> entradas = entradaService.obtenerTodasLasEntradas();
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Entrada>> buscarPorFecha(
            @RequestParam("desde") String desdeStr,
            @RequestParam("hasta") String hastaStr){

        LocalDate desde = LocalDate.parse(desdeStr);
        LocalDate hasta = LocalDate.parse(hastaStr);

        List<Entrada> resultado = entradaService.buscarPorFechas(desde, hasta);
        return ResponseEntity.ok(resultado);
    }
}
