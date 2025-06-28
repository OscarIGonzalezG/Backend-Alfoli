package com.alfoli.alfoli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumenCategoriaResponse {

    private String categoria;
    private int cantidadTotal;
}
