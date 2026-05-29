package com.example.resena.Controller;

import com.example.resena.Model.Resena;
import com.example.resena.Service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;



//URL: http://localhost:8088/resenas/crear

//{
//       "idUsuario": 1,
//       "idJuego": 2,
//       "comentario": "Excelente juego, la historia te atrapa desde el primer minuto.",
//       "calificacion": 5
   //}

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService service;

    @PostMapping("/crear")
    public ResponseEntity<Resena> crear(@Valid @RequestBody Resena resena) {
        Resena guardada = service.guardarResena(resena);
        
        if (guardada != null) {
            return ResponseEntity.status(201).body(guardada);
        }
        
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/juego/{idJuego}")
    public List<Resena> listarPorJuego(@PathVariable Integer idJuego) {
        return service.obtenerResenasPorJuego(idJuego);
    }
}