package com.notificaciones.notificaciones.config;

import com.notificaciones.notificaciones.model.Notificacion;
import com.notificaciones.notificaciones.service.NotificacionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(NotificacionService service) {
        return args -> {
            if (service.count() == 0) {

                service.save(new Notificacion(null, 1, "Se han agregado 3 videojuegos nuevos", false, LocalDateTime.now()));

                crearNotificacionSimple(service, 1, "Tu pedido #98765 acaba de ser confirmado.");
                
                System.out.println("--- Notificaciones iniciales cargadas correctamente ---");
            }
        };
    }

    private void crearNotificacionSimple(NotificacionService service, Integer usuarioId, String mensaje) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(usuarioId); // Ahora recibe Integer
        notificacion.setMensaje(mensaje);
        notificacion.setLeido(false);
        notificacion.setFechaCreacion(LocalDateTime.now());
        service.save(notificacion);
    }
}