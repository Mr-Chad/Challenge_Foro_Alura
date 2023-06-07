package com.foro.alura.api.modelo.curso;

public record ListadoCurso(Long id, String nombre, String categoria) {
    public ListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
