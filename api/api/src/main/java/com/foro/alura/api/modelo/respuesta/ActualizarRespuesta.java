package com.foro.alura.api.modelo.respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuesta(@NotNull Long id, String mensaje,
                                  Boolean solucion, Long topico, Long autor) {

}
