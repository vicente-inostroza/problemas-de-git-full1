package com.example.favoritos.repository;

import com.example.favoritos.model.JuegoFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<JuegoFavorito, Integer> {
    
    List<JuegoFavorito> findByIdUsuario(Integer idUsuario);
}