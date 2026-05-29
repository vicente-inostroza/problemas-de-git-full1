package com.example.resena.Repository;

import com.example.resena.Model.Resena;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findByIdJuego(Integer idJuego);
}