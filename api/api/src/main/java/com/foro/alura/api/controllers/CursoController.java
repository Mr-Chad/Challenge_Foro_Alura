package com.foro.alura.api.controllers;

import com.foro.alura.api.infra.errores.TratadorDeErrores;
import com.foro.alura.api.modelo.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/curso")
public class CursoController {

    @Autowired
    private cursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity registrarCurso(@RequestBody @Valid RegistroCurso registroCurso,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(registroCurso));
        RespuestaCurso datosRespuestaCurso = new RespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoCurso>> listadoCurso(@PageableDefault(size = 10, sort = "Id") Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(ListadoCurso::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCurso> RetornaDatosCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new RespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
        return ResponseEntity.ok(datosCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        if (cursoRepository.existsById(id)) {
            Curso curso = cursoRepository.getReferenceById(id);
            cursoRepository.delete(curso);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
    }
}
