package com.example.soporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets_soporte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket", nullable = false, updatable = false)
    private Integer idTicket;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "El asunto no puede estar vacío")
    @Column(name = "asunto", nullable = false)
    private String asunto;

    @NotNull(message = "La descripción del problema es obligatoria")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado")
    private String estado; // Ejemplo: "Abierto", "En Proceso", "Resuelto"
}