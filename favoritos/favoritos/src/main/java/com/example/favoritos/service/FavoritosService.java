package com.example.favoritos.service;

import com.example.favoritos.model.DatosJuegoCatalogo;
import com.example.favoritos.model.JuegoFavorito;

import com.example.favoritos.repository.FavoritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FavoritosService {

    @Autowired
    private FavoritosRepository repository;

    // Consulta por HTTP al catálogo para verificar la existencia del juego antes de agregarlo a la lista de favoritos
    public JuegoFavorito agregarAFavoritos(JuegoFavorito favorito) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/catalogo/buscar-id/" + favorito.getIdJuego();
        
        DatosJuegoCatalogo juego = restTemplate.getForObject(url, DatosJuegoCatalogo.class);
        
        if (juego != null) {
            return repository.save(favorito);
        }
        
        return null; 
    }

    // Recupera la lista completa de juegos marcados como favoritos por un usuario en específico
    public List<JuegoFavorito> obtenerFavoritosPorUsuario(Integer idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }
}