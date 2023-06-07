package com.foro.alura.api.modelo.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface topicoRepository extends JpaRepository<Topico, Long >{

    @Query("SELECT T FROM Topico T WHERE T.autor.id=:autor")
    Page<Topico> buscarPorAutor(Long autor, Pageable paginacion);

    @Query("SELECT T FROM Topico T WHERE T.curso.id=:curso")
    Page<Topico> buscarPorCurso(Long curso, Pageable paginacion);
}
