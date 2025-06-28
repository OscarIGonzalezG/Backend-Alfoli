package com.alfoli.alfoli.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SalidaRequest {

    private LocalDate fecha;
    private String entregadoA;
    private String observacion;
    private String registradoPor;
    private List<SalidaDetalleDto> detalles;
}
