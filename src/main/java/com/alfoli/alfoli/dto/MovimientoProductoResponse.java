package com.alfoli.alfoli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MovimientoProductoResponse {

    private LocalDate fecha;
    private String tipo; // "ENTRADA" o "SALIDA"
    private int cantidad;
    private String registradoPor;
}
