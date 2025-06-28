package com.alfoli.alfoli.service;

import com.alfoli.alfoli.dto.SalidaRequest;
import com.alfoli.alfoli.dto.SalidaDetalleDto;
import com.alfoli.alfoli.entity.Producto;
import com.alfoli.alfoli.entity.Salida;
import com.alfoli.alfoli.entity.SalidaDetalle;
import com.alfoli.alfoli.repository.ProductoRepository;
import com.alfoli.alfoli.repository.SalidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository salidaRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public Salida registrarSalidaDesdeDto(SalidaRequest dto){
        Salida salida = new Salida();
        salida.setFecha(dto.getFecha());
        salida.setEntregadoA(dto.getEntregadoA());
        salida.setObservacion(dto.getObservacion());
        salida.setRegistradoPor(dto.getRegistradoPor());

        List<SalidaDetalle> detalles = new ArrayList<>();

        for (SalidaDetalleDto detalleDto : dto.getDetalles()){
            Producto producto = productoRepository.findById(detalleDto.getProductoId())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado con ID: " + detalleDto.getProductoId()));

            if (producto.getStock() < detalleDto.getCantidad()){
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() + detalleDto.getCantidad());
            productoRepository.save(producto);

            SalidaDetalle detalle = new SalidaDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setSalida(salida);

            detalles.add(detalle);

        }

        salida.setDetalles(detalles);
        return salidaRepository.save(salida);
    }

    public List<Salida> obtenerTodasLasSalidas(){
        return salidaRepository.findAll();
    }

    public List<Salida> buscarPorFechas(LocalDate desde, LocalDate hasta) {
        return salidaRepository.findByFechaBetween(desde, hasta);
    }
}
