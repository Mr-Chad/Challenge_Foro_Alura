package com.foro.alura.api.controllers;

import com.foro.alura.api.modelo.curso.Curso;
import com.foro.alura.api.modelo.topico.*;
import com.foro.alura.api.modelo.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/topico")
public class TopicoController {

    @Autowired
    private topicoRepository topicoRepository;


    @PostMapping
    public ResponseEntity registrarTopico (@RequestBody @Valid RegistroTopico registroTopico, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(registroTopico));
        RespuestaTopico RespuestaTopico = new RespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new Usuario(topico.getAutor().getId()).getId(),
                new Curso(topico.getCurso().getId()).getId()
        );
        URI url = uriComponentsBuilder.path("/foro/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(RespuestaTopico);
    };



}
