package com.foro.alura.api.modelo.topico;

public record RegistroTopico(String titulo, String mensaje, StatusTopico status, Long autor, Long curso) {
}
