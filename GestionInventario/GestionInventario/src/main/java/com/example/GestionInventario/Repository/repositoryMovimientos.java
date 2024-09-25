package com.example.GestionInventario.Repository;

import com.example.GestionInventario.Model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryMovimientos extends JpaRepository<Movimientos, Long> {
}

