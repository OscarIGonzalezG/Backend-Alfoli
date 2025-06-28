package com.alfoli.alfoli.service;

import com.alfoli.alfoli.entity.Entrada;
import com.alfoli.alfoli.entity.EntradaDetalle;
import com.alfoli.alfoli.entity.Producto;
import com.alfoli.alfoli.repository.EntradaRepository;
import com.alfoli.alfoli.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfoli.alfoli.dto.EntradaRequest;
import com.alfoli.alfoli.dto.EntradaDetalleDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final ProductoRepository productoRepository;

    @Transactional // Se Guarda todo.
    public Entrada registrarEntrada(Entrada entrada){
        // Por cada producto recibido...
        for (EntradaDetalle detalle : entrada.getDetalles()){
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getId()));

            // Aumentar Stock
            producto.setStock(producto.getStock() + detalle.getCantidad());
            productoRepository.save(producto);

            // Guardar Actualizacióm
            detalle.setProducto(producto);

            // Relación inversa
            detalle.setEntrada(entrada);
        }

        // Guardar entrada completa con detalles
        return entradaRepository.save(entrada);
    }

    @Transactional
    public Entrada registrarEntradaDesdeDto(EntradaRequest dto){
        Entrada entrada = new Entrada();
        entrada.setFecha(dto.getFecha());
        entrada.setRegistradoPor(dto.getRegistradoPor());
        entrada.setEntregadoPor(dto.getEntregadoPor());

        List<EntradaDetalle> detalles = new ArrayList<>();

        for (EntradaDetalleDto detalleDto : dto.getDetalles()){
            Producto producto = productoRepository.findById(detalleDto.getProductoId())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado con ID: " + detalleDto.getProductoId()));

            // Aumentar Stock
            producto.setStock(producto.getStock() + detalleDto.getCantidad());
            productoRepository.save(producto);

            // Crear detalle
            EntradaDetalle detalle = new EntradaDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setEntrada(entrada);

            detalles.add(detalle);
        }

        entrada.setDetalles(detalles);
        return entradaRepository.save(entrada);

    }

    public List<Entrada> obtenerTodasLasEntradas(){
        return entradaRepository.findAll();
    }

    public List<Entrada> buscarPorFechas(LocalDate desde, LocalDate hasta) {
        return entradaRepository.findByFechaBetween(desde, hasta);

    }

}
