package com.foro.alura.api.modelo.respuesta;

import java.time.LocalDateTime;

public record RtaRespuesta(Long id, String mensaje, LocalDateTime fecha_creacion,
                           Boolean solucion, Long topico, Long autor) {
}
