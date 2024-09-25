package com.example.GestionInventario.Controller;

import com.example.GestionInventario.Model.Movimientos;
import com.example.GestionInventario.Model.Productos;
import com.example.GestionInventario.Service.InventarioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControllerManager {



        @Autowired
        public InventarioManager productosService;

        //Obtener los productos
        @GetMapping ("/producto")
        public List<Productos> getAllProductos(){
            return productosService.getAllProductos();
        }

        // obtener por id producto
        @GetMapping("/producto/{id}")
        public Optional<Productos> getProductoById(@PathVariable long id){
            return productosService.getProductoById(id);
        }

        // Guardar y actualizar los productos
        @PostMapping("/producto")
        public Productos updateProductos(@RequestBody Productos productos){
            return productosService.createProducto(productos);
        }

        // Eliminar productos
        @DeleteMapping("/producto/{id}")
        public void deleteProductos(@PathVariable long id){
            productosService.deleteProducto(id);
        }

    @PutMapping("/producto/{id}")
    public Productos actualizarProductos(@PathVariable long id,@RequestBody Productos productos) {
        return productosService.saveProducto(id, productos);}



    @Autowired
    public InventarioManager movimientosService;

    //Obtener los movimientos
    @GetMapping("/movimientos")
    public List<Movimientos> getAllMovimientos(){
        return movimientosService.getAllMovimientos();
    }

    @GetMapping("/movimiento/{id}")
    public Optional<Movimientos> getMovimientoById(@PathVariable long id){
        return movimientosService.getMovimientoById(id);
    }

    // Guardar y actualizar los movimientos
    @PostMapping("/movimientos")
    public Movimientos updateMovimiento (@RequestBody Movimientos movimientos){
        return movimientosService.createMovimiento(movimientos);
    }

    // Eliminar productos
    @DeleteMapping("/movimientos/{id}")
    public void deleteMovimiento(@PathVariable long id){
        movimientosService.deleteMovimiento(id);
    }

}







