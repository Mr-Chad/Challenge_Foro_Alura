package com.foro.alura.api.modelo.respuesta;

public record ListadoRespuesta(
        Long id,
        String mensaje,
        String fechaCreacion,
        Boolean solucion) {

    public ListadoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
    }

}