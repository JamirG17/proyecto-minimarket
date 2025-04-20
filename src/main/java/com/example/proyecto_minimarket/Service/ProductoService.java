package com.example.proyecto_minimarket.Service;

import com.example.proyecto_minimarket.Model.Producto;
import com.example.proyecto_minimarket.Repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repositorio;

    public ProductoService(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Producto agregar(Producto producto) {
        return repositorio.guardar(producto);
    }

    public List<Producto> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public ResponseEntity<?> actualizar(Long id, Producto nuevo) {
        return repositorio.actualizar(id, nuevo);
    }

    public ResponseEntity<?> actualizarParcial(Long id, Producto parcial) {
        Producto existente = repositorio.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.status(404).body("Producto no encontrado con ID: " + id);
        }

        if (parcial.getNombre() != null && !parcial.getNombre().trim().isEmpty()) {
            existente.setNombre(parcial.getNombre().trim());
        } else if (parcial.getNombre() != null) {
            return ResponseEntity.badRequest().body("El nombre no puede estar vacío o en blanco.");
        }

        if (parcial.getPrecio() != null && parcial.getPrecio() > 0) {
            existente.setPrecio(parcial.getPrecio());
        } else if (parcial.getPrecio() != null) {
            return ResponseEntity.badRequest().body("El precio debe ser mayor que cero.");
        }

        if (parcial.getCategoria() != null && !parcial.getCategoria().trim().isEmpty()) {
            existente.setCategoria(parcial.getCategoria().trim());
        } else if (parcial.getCategoria() != null) {
            return ResponseEntity.badRequest().body("La categoría no puede estar vacía o en blanco.");
        }

        if (parcial.getStock() != null && parcial.getStock() >= 0) {
            existente.setStock(parcial.getStock());
        } else if (parcial.getStock() != null) {
            return ResponseEntity.badRequest().body("El stock no puede ser negativo.");
        }

        return ResponseEntity.ok("Producto actualizado parcialmente con ID: " + id);
    }

    public ResponseEntity<String> eliminar(Long id) {
        return repositorio.eliminar(id);
    }
} 
