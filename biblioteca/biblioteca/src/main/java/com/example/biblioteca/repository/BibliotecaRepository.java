package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.biblioteca.model.BibliotecaModel;

// El REPOSITORIO es el puente directo con la base de datos.
// Al heredar de JpaRepository, nos da gratis los métodos básicos para
// guardar, buscar, actualizar o eliminar datos sin escribir código SQL.
@Repository
public interface BibliotecaRepository extends JpaRepository<BibliotecaModel, Integer> {
    // Vacío. Hereda automáticamente las operaciones de guardar y listar.
}