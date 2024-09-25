package com.example.GestionInventario.Service;

import com.example.GestionInventario.Model.Productos;
import com.example.GestionInventario.Model.Movimientos;
import com.example.GestionInventario.Repository.repositoryProductos;
import com.example.GestionInventario.Repository.repositoryMovimientos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioManager {

    @Autowired
    private repositoryProductos repositoryproductos;

    @Autowired
    private repositoryMovimientos repositorymovimientos;

    // CRUD para Productos

    // Crear Producto
    public Productos createProducto(Productos producto) {
        return repositoryproductos.save(producto);
    }

    // Leer todos los Productos
    public List<Productos> getAllProductos() {
        return repositoryproductos.findAll();
    }

    // Leer Producto por ID
    public Optional<Productos> getProductoById(Long idProducto) {
        return repositoryproductos.findById(idProducto);
    }

    // Actualizar Producto
    public Productos updateProducto(Long idProductos, Productos productoDetalles) {
        return repositoryproductos.findById(idProductos).map(producto -> {
            producto.setNombre_producto(productoDetalles.getNombre_producto());
            producto.setDescripcion(productoDetalles.getDescripcion());
            producto.setPrecio(productoDetalles.getPrecio());
            return repositoryproductos.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    public Productos saveProducto (long id, Productos productos){
        return repositoryproductos.save(productos);
    }

    // Eliminar Producto
    public void deleteProducto(Long id) {
        repositorymovimientos.deleteById(id);
    }

    // CRUD para Movimientos

    // Crear Movimiento
    public Movimientos createMovimiento(Movimientos movimiento) {
        return repositorymovimientos.save(movimiento);
    }

    // Leer todos los Movimientos
    public List<Movimientos> getAllMovimientos() {
        return repositorymovimientos.findAll();
    }

    // Leer Movimiento por ID
    public Optional<Movimientos> getMovimientoById(Long idMovimiento) {
        return repositorymovimientos.findById(idMovimiento);
    }



    // Actualizar Movimiento
    public Movimientos updateMovimiento(Long idMovimiento, Movimientos movimientoDetalles) {
        return repositorymovimientos.findById(idMovimiento).map(movimiento -> {
            movimiento.setCantidad(movimientoDetalles.getCantidad());
            movimiento.setTipoMovimiento(movimientoDetalles.getTipoMovimiento());
            movimiento.setFecha(movimientoDetalles.getFecha());
            movimiento.setId_producto(movimientoDetalles.getId_producto());
            return repositorymovimientos.save(movimiento);
        }).orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }
    public Movimientos saveMovimiento (Movimientos movimientos){
        return repositorymovimientos.save(movimientos);
    }

    // Eliminar Movimiento
    public void deleteMovimiento(Long id) {
        repositorymovimientos.deleteById(id);
    }
}
