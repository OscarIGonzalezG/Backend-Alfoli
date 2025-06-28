package com.alfoli.alfoli.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;


@Entity // Marca la clase como tabla JPA
@Table(name = "productos") // Nombre de la tabla
@Getter // Método devuelven el valor de un atributo privado de una clase
@Setter // Método permiten cambiar el valor de un atributo privado de una clase
@NoArgsConstructor // Genera un constructor sin parámetros.
@AllArgsConstructor // Genera un constructor que inicializa todos los campos de la clase
@Builder // Método devuelve una instancia de un builder (constructor paso a paso) para tu clase
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Size(min = 3, message = "La categoría debe tener al menos 3 caracteres")
    private String categoria;

    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private int stock;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private String unidadMedida; // ej: "kg", "litros", "paquetes"


}
