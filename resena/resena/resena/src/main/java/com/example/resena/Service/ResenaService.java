package com.example.resena.Service;

import com.example.resena.Model.Resena;
import com.example.resena.Repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository repository;

    public Resena guardarResena(Resena resena) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/catalogo/buscar-id/" + resena.getIdJuego();

        Object juego = restTemplate.getForObject(url, Object.class);

        if (juego != null) {
            return repository.save(resena);
        }

        return null;
    }

    public List<Resena> obtenerResenasPorJuego(Integer idJuego) {
        return repository.findByIdJuego(idJuego);
    }
}

