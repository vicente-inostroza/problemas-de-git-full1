package com.example.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.biblioteca.model.BibliotecaModel;
import com.example.biblioteca.service.BibliotecaService;
import java.util.List;

// El CONTROLADOR es la puerta de entrada del microservicio (la API).
// Se encarga de escuchar las peticiones que vienen desde fuera (como Postman o el navegador),
// capturar las rutas (/crear, /listar) y entregar la respuesta final en formato JSON.
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaService service;

    @PostMapping("/crear")
    public ResponseEntity<BibliotecaModel> crear(@RequestBody BibliotecaModel juego) {
        BibliotecaModel nuevoJuego = service.guardarJuego(juego);
        return ResponseEntity.status(201).body(nuevoJuego);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<BibliotecaModel>> listar() {
        List<BibliotecaModel> lista = service.obtenerTodos();
        return ResponseEntity.ok(lista);
    }
}