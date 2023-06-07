package com.foro.alura.api.modelo.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRespuesta(@NotBlank String mensaje,
                                @NotNull Long topico, @NotNull Long autor) {
}
