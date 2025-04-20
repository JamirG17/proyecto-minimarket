package com.example.proyecto_minimarket.Controller;

import com.example.proyecto_minimarket.Model.Producto;
import com.example.proyecto_minimarket.Service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService servicio;

    @Autowired
    public ProductoController(ProductoService servicio){
        this.servicio = servicio;
    }

    @GetMapping
    public List<Producto> listarProductos(){
        return servicio.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto){
        return ResponseEntity.ok(servicio.agregar(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto nuevoProducto){
        return servicio.actualizar(id, nuevoProducto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcialmenteProducto(@PathVariable Long id, @RequestBody Producto productoParcial) {
        return servicio.actualizarParcial(id, productoParcial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        return servicio.eliminar(id);
    }
} 
