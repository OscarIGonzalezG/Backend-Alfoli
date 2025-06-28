package com.alfoli.alfoli.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salida_detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalidaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "producto:id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "salida_id")
    private Salida salida;
}
