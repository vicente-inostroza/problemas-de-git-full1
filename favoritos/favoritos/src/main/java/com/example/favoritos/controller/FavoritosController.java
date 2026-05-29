package com.example.favoritos.controller;

import com.example.favoritos.model.JuegoFavorito;
import com.example.favoritos.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    @Autowired
    private FavoritosService service;

    // Recibe la petición para marcar un juego como favorito y valida su existencia previa en la tienda
    @PostMapping("/agregar")
    public ResponseEntity<JuegoFavorito> agregar(@Valid @RequestBody JuegoFavorito favorito) {
        JuegoFavorito guardado = service.agregarAFavoritos(favorito);
        
        if (guardado != null) {
            return ResponseEntity.status(201).body(guardado);
        }
        
        return ResponseEntity.status(400).body(null);
    }

    // Expone los juegos favoritos que pertenecen a un código de usuario determinado
    @GetMapping("/usuario/{idUsuario}")
    public List<JuegoFavorito> listarPorUsuario(@PathVariable Integer idUsuario) {
        return service.obtenerFavoritosPorUsuario(idUsuario);
    }
}