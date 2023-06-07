package com.foro.alura.api.modelo.curso;

import jakarta.validation.constraints.NotNull;

public record RegistroCurso(@NotNull String nombre, @NotNull String categoria) {
}
