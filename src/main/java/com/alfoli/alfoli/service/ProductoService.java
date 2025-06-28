package com.alfoli.alfoli.service;

import com.alfoli.alfoli.dto.MovimientoProductoResponse;
import com.alfoli.alfoli.dto.ResumenCategoriaResponse;
import com.alfoli.alfoli.entity.EntradaDetalle;
import com.alfoli.alfoli.entity.Producto;
import com.alfoli.alfoli.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alfoli.alfoli.entity.Entrada;
import com.alfoli.alfoli.entity.Salida;
import com.alfoli.alfoli.entity.SalidaDetalle;
import com.alfoli.alfoli.dto.MovimientoProductoResponse;
import com.alfoli.alfoli.repository.EntradaRepository;
import com.alfoli.alfoli.repository.SalidaRepository;
import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EntradaRepository entradaRepository;
    private final SalidaRepository salidaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           EntradaRepository entradaRepository,
                           SalidaRepository salidaRepository){
        this.productoRepository = productoRepository;
        this.entradaRepository = entradaRepository;
        this.salidaRepository = salidaRepository;
    }

    // Guardar producto
    public Producto guardarProducto(Producto producto){

        return productoRepository.save(producto);
    }

    // Listar todos los productos
    public List<Producto> obtenerTodos(){
        return productoRepository.findAll();
    }


    public List<MovimientoProductoResponse> obtenerMovimientosPorProducto(Long productoId){
        List<MovimientoProductoResponse> movimientos = new ArrayList<>();

        // Entradas
        List<Entrada> entradas = entradaRepository.findAll();
        for (Entrada entrada : entradas){
            for (EntradaDetalle detalle : entrada.getDetalles()){
                if (detalle.getProducto().getId().equals(productoId)){
                    movimientos.add(new MovimientoProductoResponse(
                            entrada.getFecha(),
                            "ENTRADA",
                            detalle.getCantidad(),
                            entrada.getRegistradoPor()
                    ));
                }
            }
        }

        // Salidas
        List<Salida> salidas = salidaRepository.findAll();
        for (Salida salida : salidas) {
            for (SalidaDetalle detalle : salida.getDetalles()) {
                if (detalle.getProducto().getId().equals(productoId)) {
                    movimientos.add(new MovimientoProductoResponse(
                            salida.getFecha(),
                            "SALIDA",
                            detalle.getCantidad(),
                            salida.getRegistradoPor()
                    ));
                }
            }
        }

        // Ordenar por fecha descendente
        movimientos.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

        return movimientos;
    }

    public List<ResumenCategoriaResponse> obtenerResumenPorCategoria(){
        List<Producto> productos = productoRepository.findAll();

        Map<String, Integer> resumen = new HashMap<>();

        for (Producto producto : productos) {
            String categoria = producto.getCategoria() != null ? producto.getCategoria() : "Sin categoría";
            int cantidad = producto.getStock();

            resumen.put(categoria, resumen.getOrDefault(categoria, 0) + cantidad);
        }

        // Convertimos a lista de DTOs
        return resumen.entrySet().stream()
                .map(entry -> new ResumenCategoriaResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
