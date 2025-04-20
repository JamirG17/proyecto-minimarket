package com.example.proyecto_minimarket;

import com.example.proyecto_minimarket.Model.Producto;
import com.example.proyecto_minimarket.Repository.ProductoRepository;
import com.example.proyecto_minimarket.Service.ProductoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTest {
    @Test
    void testAgregarProducto() {
        ProductoRepository repo = new ProductoRepository();
        ProductoService servicio = new ProductoService(repo);
        Producto producto = new Producto(null, "Aceite", 12.5, "Alimentos", 10);

        Producto guardado = servicio.agregar(producto);

        assertNotNull(guardado.getId());
        assertEquals("Aceite", guardado.getNombre());
        assertEquals(12.5, guardado.getPrecio());
        assertEquals("Alimentos", guardado.getCategoria());
        assertEquals(10, guardado.getStock());
    }
} 
