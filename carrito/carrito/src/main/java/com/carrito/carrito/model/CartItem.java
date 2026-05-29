package com.carrito.carrito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart_item", nullable = false, updatable = false)
    private Integer idCartItem;

    @NotNull(message = "El ID del usuario no puede estar vacío")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull(message = "El ID del juego no puede estar vacío")
    @Column(name = "id_game", nullable = false)
    private Integer idGame;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}