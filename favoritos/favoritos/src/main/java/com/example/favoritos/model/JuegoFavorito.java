package com.example.favoritos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favoritos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JuegoFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_juego_favorito", nullable = false, updatable = false)
    private Integer idJuegoFavorito;

    @NotNull(message = "El ID del usuario no puede estar vacío")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "El ID del juego no puede estar vacío")
    @Column(name = "id_juego", nullable = false)
    private Integer idJuego;
}