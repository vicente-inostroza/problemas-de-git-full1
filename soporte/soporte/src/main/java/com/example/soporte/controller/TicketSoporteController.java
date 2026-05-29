package com.example.soporte.controller;

import com.example.soporte.model.TicketSoporte;
import com.example.soporte.service.TicketSoporteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;




//URL: http://localhost:8089/soporte/ticket/abrir
//{
    //   "idUsuario": 1,
    //   "asunto": "Problema con descarga",
    //   "descripcion": "El juego se queda pegado en el 99% de la instalación y tira error."
  // }
@RestController
@RequestMapping("/soporte")
public class TicketSoporteController {

    @Autowired
    private TicketSoporteService service;

    // Registra un nuevo ticket de soporte si el usuario es válido
    @PostMapping("/ticket/abrir")
    public ResponseEntity<TicketSoporte> abrirTicket(@Valid @RequestBody TicketSoporte ticket) {
        TicketSoporte guardado = service.crearTicket(ticket);
        
        if (guardado != null) {
            return ResponseEntity.status(201).body(guardado);
        }
        
        return ResponseEntity.status(400).body(null);
    }

    // Lista todos los tickets creados por un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<TicketSoporte> listarPorUsuario(@PathVariable Integer idUsuario) {
        return service.obtenerTicketsPorUsuario(idUsuario);
    }
}