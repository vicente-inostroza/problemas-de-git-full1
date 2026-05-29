package com.example.favoritos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.favoritos.model.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    // Spring Data JPA creará automáticamente una consulta para este método
    List<Favorito> findByUsuarioId(String usuarioId);

    // Método para verificar si ya existe un favorito con un usuario y juego específicos
    boolean existsByUsuarioIdAndJuegoId(String usuarioId, String juegoId);
}