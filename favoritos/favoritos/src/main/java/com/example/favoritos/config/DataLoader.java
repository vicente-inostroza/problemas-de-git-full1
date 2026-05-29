package com.example.favoritos.config;

import com.example.favoritos.model.Favorito;
import com.example.favoritos.repository.FavoritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Cargar datos solo si la tabla está vacía
        if (favoritoRepository.count() == 0) {
            log.info("La base de datos está vacía. Cargando datos de ejemplo para favoritos...");

            Favorito fav1 = new Favorito(null, "1", "1"); // Asumimos que el juego 1 existe
            Favorito fav2 = new Favorito(null, "3", "3"); // Asumimos que el juego 3 existe
            Favorito fav3 = new Favorito(null, "2", "1");   // Asumimos que el juego 1 existe

            favoritoRepository.saveAll(Arrays.asList(fav1, fav2, fav3));

            log.info("Datos de favoritos cargados. Total: {}", favoritoRepository.count());
        } else {
            log.info("La base de datos de favoritos ya contiene datos. No se cargaron datos de ejemplo.");
        }
    }
}