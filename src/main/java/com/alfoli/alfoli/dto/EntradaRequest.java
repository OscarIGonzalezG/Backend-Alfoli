package com.alfoli.alfoli.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EntradaRequest {

    private LocalDate fecha;
    private String entregadoPor;
    private String registradoPor;
    private List<EntradaDetalleDto> detalles;


}
