package com.carrito.carrito.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class GameData {
    private Integer idGame;
    private String titulo;
    private String descripcion;
    private BigDecimal precio;
    private String categoria;
}