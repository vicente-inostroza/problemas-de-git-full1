package com.example.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.biblioteca.model.BibliotecaModel;
import com.example.biblioteca.repository.BibliotecaRepository;
import java.util.List;

// El SERVICIO es el cerebro del microservicio; aquí va la lógica de negocio.
// Se encarga de recibir los datos, procesarlos o aplicar reglas si fuera necesario,
// y llamar al Repositorio para que interactúe con la base de datos.
@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;

    public BibliotecaModel guardarJuego(BibliotecaModel juego) {
        return repository.save(juego);
    }

    public List<BibliotecaModel> obtenerTodos() {
        return repository.findAll();
    }
}