package com.example.proyecto_minimarket.Repository;

import com.example.proyecto_minimarket.Model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductoRepository {
    private final Map<Long, Producto> db = new HashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public Producto guardar(Producto producto) {
        producto.setId(secuencia.getAndIncrement());
        db.put(producto.getId(), producto);
        return producto;
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(db.values());
    }

    public ResponseEntity<?> actualizar(Long id, Producto nuevo) {
        if (db.containsKey(id)) {
            nuevo.setId(id);
            db.put(id, nuevo);
            return ResponseEntity.ok(nuevo);
        }
        return ResponseEntity.status(404).body("Producto no encontrado");
    }

    public Producto buscarPorId(Long id) {
        return db.get(id);
    }

    public ResponseEntity<String> eliminar(Long id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return ResponseEntity.ok("Producto eliminado");
        }
        return ResponseEntity.status(404).body("Producto no encontrado");
    }
} 