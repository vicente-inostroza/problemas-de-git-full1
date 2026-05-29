package com.example.biblioteca.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// El MODELO representa la estructura de los datos en nuestra aplicación.
// En este caso, define qué información vamos a guardar de un juego (su ID y su Título) 
// y le avisa a la base de datos cómo debe crear la tabla.
@Data
@Entity
@Table(name = "biblioteca_juegos")
public class BibliotecaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El título no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String titulo;

}