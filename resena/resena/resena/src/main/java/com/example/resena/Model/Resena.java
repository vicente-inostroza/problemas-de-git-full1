package com.example.resena.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena", nullable = false, updatable = false)
    private Integer idResena;

    @NotNull(message = "El ID del usuario no puede estar vacío")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "El ID del juego no puede estar vacío")
    @Column(name = "id_juego", nullable = false)
    private Integer idJuego;

    @NotNull(message = "El comentario no puede estar vacío")
    @Column(name = "comentario", nullable = false)
    private String comentario;

    @NotNull(message = "La calificación no puede estar vacía")
    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;
} 

