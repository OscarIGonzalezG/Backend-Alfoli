package com.alfoli.alfoli.entity;

import com.alfoli.alfoli.enums.EstadoNecesidad;
import com.alfoli.alfoli.enums.Prioridad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "necesidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Necesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productoSolicitado; //Nombre libre, o luego relación con Producto

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private Prioridad prioridad; //ALTA, MEDIA, BAJA

    @Enumerated(EnumType.STRING)
    private EstadoNecesidad estado; // PENDIENTE o CUMPLIDA

    private String observaciones;

    private LocalDate fechaSolicitud;
}
