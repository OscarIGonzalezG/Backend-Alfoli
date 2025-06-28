package com.alfoli.alfoli.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrada_detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntradaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    // Producto recibido
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Entrada a la que pertenece
    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private Entrada entrada;
}
