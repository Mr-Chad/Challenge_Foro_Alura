package com.foro.alura.api.modelo.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopico(@NotNull Long id, String titulo, String mensaje,
                               StatusTopico estado, Long autor, Long curso) {
}
