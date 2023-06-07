package com.foro.alura.api.modelo.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface respuestaRepository extends JpaRepository<Respuesta, Long> {
    @Query("SELECT R FROM Respuesta R WHERE R.topico.id=:topico")
    Page<Respuesta> buscarPorTopico(Long topico, Pageable paginacion);

    @Query("SELECT R FROM Respuesta R WHERE R.autor.id=:autor")
    Page<Respuesta> buscarPorAutor(Long autor, Pageable paginacion);

}
