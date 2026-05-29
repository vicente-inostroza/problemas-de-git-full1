package com.example.soporte.service;


import com.example.soporte.model.TicketSoporte;
import com.example.soporte.repository.TicketSoporteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class TicketSoporteService {

    @Autowired
    private TicketSoporteRepository repository;

    // Llama de forma síncrona al micro de usuarios para verificar su existencia antes de abrir el ticket
    public TicketSoporte crearTicket(TicketSoporte ticket) {
        RestTemplate restTemplate = new RestTemplate();
        // Ajusta el puerto 8084 y la ruta si el de usuarios usa otra estructura de URL
        String url = "http://localhost:8084/usuarios/buscar-id/" + ticket.getIdUsuario();
        
        Object usuario = restTemplate.getForObject(url, Object.class);
        
        if (usuario != null) {
            ticket.setEstado("Abierto"); // Asignamos estado inicial por defecto
            return repository.save(ticket);
        }
        
        return null;
    }

    public List<TicketSoporte> obtenerTicketsPorUsuario(Integer idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }
}