package com.alfoli.alfoli.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "salidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String entregadoA; // Ej: "Familia González"

    private String observacion; // Opcional

    private String registradoPor;

    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalidaDetalle> detalles;
}
