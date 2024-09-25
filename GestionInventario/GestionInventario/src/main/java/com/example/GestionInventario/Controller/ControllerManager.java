package com.example.GestionInventario.Controller;

import com.example.GestionInventario.Model.Productos;
import com.example.GestionInventario.Service.InventarioManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ControllerManager {



        @Autowired
        public InventarioManager productosService;

        //Obtener los productos
        @GetMapping
        public List<Productos> getAllProductos(){
            return productosService.getAllProductos();
        }

        // Guardar y actualizar los productos
        @PostMapping
        public Productos createProductos(@RequestBody Productos productos){
            return productosService.updateProducto(productos);
        }

        // Eliminar productos
        @DeleteMapping("/{id}")
        public void deleteProductos(@PathVariable Long id){
            productosService.deleteProducto(id);
        }
    }

