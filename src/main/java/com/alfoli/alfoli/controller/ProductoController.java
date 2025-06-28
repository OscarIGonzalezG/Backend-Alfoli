package com.alfoli.alfoli.controller;

import com.alfoli.alfoli.dto.ResumenCategoriaResponse;
import com.alfoli.alfoli.entity.Producto;
import com.alfoli.alfoli.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite llamadas desde Angular u otros orígenes.
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    // POST: Crear un nuevo producto
    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        Producto guardado = productoService.guardarProducto(producto);
        return ResponseEntity.status(201).body(guardado);

    }

    // GET: Obtener todos los productos
    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.obtenerTodos();
    }

    @GetMapping("/resumen-categorias")
    public ResponseEntity<List<ResumenCategoriaResponse>> obtenerResumen(){
        List<ResumenCategoriaResponse> resumen = productoService.obtenerResumenPorCategoria();
        return ResponseEntity.ok(resumen);
    }
}
