package com.foro.alura.api.modelo.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String email,
        String contrasena) {

}
