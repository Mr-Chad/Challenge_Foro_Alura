package com.foro.alura.api.modelo.topico;

import com.foro.alura.api.modelo.curso.Curso;
import com.foro.alura.api.modelo.usuario.Usuario;

import java.time.LocalDateTime;

public record ListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion,
                            StatusTopico status, Usuario autor, Curso curso) {
}
