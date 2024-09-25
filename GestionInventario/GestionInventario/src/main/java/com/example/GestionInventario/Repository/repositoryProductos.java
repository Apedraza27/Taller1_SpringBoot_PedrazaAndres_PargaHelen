package com.example.GestionInventario.Repository;

import com.example.GestionInventario.Model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryProductos extends JpaRepository<Productos, Long> {
}


