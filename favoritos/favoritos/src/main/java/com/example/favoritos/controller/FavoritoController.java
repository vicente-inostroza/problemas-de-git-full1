package com.example.favoritos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.favoritos.model.Favorito;
import com.example.favoritos.dto.ApiResponse;
import com.example.favoritos.service.FavoritoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/favoritos")

public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    // - POST: agregar favorito
    @PostMapping
    public ResponseEntity<ApiResponse<Favorito>> crearFavorito(@Valid @RequestBody Favorito favorito){
        // Guardar el favorito en la base de datos
        Favorito nuevoFavorito = favoritoService.guardarFavorito(favorito);
        ApiResponse<Favorito> response = new ApiResponse<>("Favorito agregado exitosamente.", nuevoFavorito);
        // Retornar 201 Created junto con el mensaje y el objeto creado
        return ResponseEntity.status(201).body(response);
    }

    // - GET: listar todos los favoritos
    @GetMapping
    public ResponseEntity<ApiResponse<List<Favorito>>> listarTodos() {
        List<Favorito> favoritos = favoritoService.obtenerTodos();
        ApiResponse<List<Favorito>> response = new ApiResponse<>("Lista de todos los favoritos obtenida.", favoritos);
        return ResponseEntity.ok(response);
    }

    // - PUT: actualizar un favorito
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Favorito>> actualizarFavorito(@PathVariable Integer id, @Valid @RequestBody Favorito favoritoDetails) {
        Favorito favoritoActualizado = favoritoService.actualizarFavorito(id, favoritoDetails);
        ApiResponse<Favorito> response = new ApiResponse<>("Favorito con ID: " + id + " actualizado con éxito.", favoritoActualizado);
        return ResponseEntity.ok(response);
    }

    // - GET: obtener un favorito por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Favorito>> obtenerPorId(@PathVariable Integer id) {
        Favorito favorito = favoritoService.obtenerPorId(id);
        ApiResponse<Favorito> response = new ApiResponse<>("Favorito con ID " + id + " encontrado.", favorito);
        return ResponseEntity.ok(response);
    }


    // - GET: listar favoritos por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<Favorito>>> listar(@PathVariable String usuarioId){
        List<Favorito> favoritos = favoritoService.obtenerPorUsuario(usuarioId);
        ApiResponse<List<Favorito>> response = new ApiResponse<>("Favoritos del usuario '" + usuarioId + "' obtenidos.", favoritos);
        // Retorna 200 OK junto con el mensaje y la lista de favoritos
        return ResponseEntity.ok(response);
    }

    // - DELETE: borrar un favorito por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> borrar(@PathVariable Integer id) {
        favoritoService.eliminar(id);
        // En lugar de 204 No Content, retornamos 200 OK con un mensaje de confirmación.
        ApiResponse<Object> response = new ApiResponse<>("Favorito con ID: " + id + " eliminado con éxito.", null);
        return ResponseEntity.ok(response);
    }

}
