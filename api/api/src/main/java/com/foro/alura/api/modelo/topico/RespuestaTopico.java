package com.foro.alura.api.modelo.topico;

import java.time.LocalDateTime;

public record RespuestaTopico(Long id, String titulo, String mensaje, LocalDateTime creacion, StatusTopico status, Long autor, Long curso) {
}
