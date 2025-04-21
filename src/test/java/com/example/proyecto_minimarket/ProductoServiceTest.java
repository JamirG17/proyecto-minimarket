package com.example.proyecto_minimarket;

import com.example.proyecto_minimarket.Model.Producto;
import com.example.proyecto_minimarket.Repository.ProductoRepository;
import com.example.proyecto_minimarket.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    @Test
    void testActualizarProducto() {
        ProductoRepository repo = new ProductoRepository();
        ProductoService servicio = new ProductoService(repo);
        Producto producto = servicio.agregar(new Producto(null, "Arroz", 8.0, "Alimentos", 20));

        Producto actualizado = new Producto(null, "Arroz Integral", 9.0, "Granos", 25);
        ResponseEntity<?> respuesta = servicio.actualizar(producto.getId(), actualizado);
        Producto resultado = (Producto) respuesta.getBody();

        assertEquals("Arroz Integral", resultado.getNombre());
        assertEquals(9.0, resultado.getPrecio());
        assertEquals("Granos", resultado.getCategoria());
        assertEquals(25, resultado.getStock());
    }

    @Test
    void testActualizarProductoParcial() {
        ProductoRepository repo = new ProductoRepository();
        ProductoService servicio = new ProductoService(repo);
        Producto producto = servicio.agregar(new Producto(null, "Fideos", 5.0, "Alimentos", 30));
    
        Producto parcial = new Producto();
        parcial.setNombre("Fideos Espirales");
    
        ResponseEntity<?> respuesta = servicio.actualizarParcial(producto.getId(), parcial);
        String mensaje = (String) respuesta.getBody();
    
        assertEquals("Producto actualizado parcialmente con ID: " + producto.getId(), mensaje);
        Producto actualizado = repo.buscarPorId(producto.getId());
        assertEquals("Fideos Espirales", actualizado.getNombre());
        assertEquals(5.0, actualizado.getPrecio());
        assertEquals("Alimentos", actualizado.getCategoria());
        assertEquals(30, actualizado.getStock());
    }

    @Test
    void testEliminarProducto() {
        ProductoRepository repo = new ProductoRepository();
        ProductoService servicio = new ProductoService(repo);
        Producto producto = servicio.agregar(new Producto(null, "Leche", 4.5, "Lácteos", 15));

        ResponseEntity<String> respuesta = servicio.eliminar(producto.getId());
        assertEquals("Producto eliminado", respuesta.getBody());

        assertNull(repo.buscarPorId(producto.getId()));
    }

    @Test
    void testListarProductos() {
        ProductoRepository repo = new ProductoRepository();
        ProductoService servicio = new ProductoService(repo);
        servicio.agregar(new Producto(null, "Galletas", 3.0, "Snacks", 50));
        servicio.agregar(new Producto(null, "Cereal", 6.0, "Desayuno", 20));

        List<Producto> productos = servicio.obtenerTodos();
        assertEquals(2, productos.size());
    }
}
