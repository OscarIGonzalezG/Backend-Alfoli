package com.alfoli.alfoli.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntradaDetalleDto {

    private Long productoId; // ID del producto que se va a ingresar.
    private int cantidad; // Cantidad del producto
}
