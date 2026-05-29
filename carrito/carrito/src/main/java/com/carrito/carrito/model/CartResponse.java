package com.carrito.carrito.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Integer idCartItem;
    private Integer idUsuario;
    private Integer cantidad;
    private GameData juego; // Aquí adentro se guarda el juego que trajiste por HTTP
}