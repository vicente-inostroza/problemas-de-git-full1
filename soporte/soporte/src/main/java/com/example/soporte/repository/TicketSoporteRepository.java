package com.example.soporte.repository;

import com.example.soporte.model.TicketSoporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketSoporteRepository extends JpaRepository<TicketSoporte, Integer> {
    List<TicketSoporte> findByIdUsuario(Integer idUsuario);
}


