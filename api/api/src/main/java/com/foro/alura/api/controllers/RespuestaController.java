package com.foro.alura.api.controllers;

import com.foro.alura.api.infra.errores.TratadorDeErrores;
import com.foro.alura.api.modelo.respuesta.*;
import com.foro.alura.api.modelo.topico.Topico;
import com.foro.alura.api.modelo.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/respuesta")
public class RespuestaController {

    private final respuestaRepository respuestaRepository;

    public RespuestaController(respuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    @PostMapping
    public ResponseEntity<RtaRespuesta> registrarRespuesta(@RequestBody @Valid RegistroRespuesta datosRegistroRespuesta,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        RtaRespuesta RtaRespuesta = new RtaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getId()).getId(),
                new Usuario(respuesta.getAutor().getId()).getId()
        );
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(RtaRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuesta(@PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(ListadoRespuesta::new));
    }

    @GetMapping("/topico/{topico}")
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuestaPorTopico(@PathVariable Long topico,
                                                                                 @PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorTopico(topico, paginacion).map(ListadoRespuesta::new));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<Page<ListadoRespuesta>> listadoRespuestaPorAutor(@PathVariable Long autor,
                                                                                @PageableDefault(size = 10, sort = "respuestaId") Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepository
                .buscarPorAutor(autor, paginacion).map(ListadoRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> RetornarDatosRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var datosRespuesta = new ListadoRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion().toString(),
                respuesta.getSolucion()
        );
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarDatos(@RequestBody @Valid ActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new RtaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                new Topico(respuesta.getTopico().getId()).getId(),
                new Usuario(respuesta.getAutor().getId()).getId())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        if (respuestaRepository.existsById(id)) {
            Respuesta respuesta = respuestaRepository.getReferenceById(id);
            respuestaRepository.delete(respuesta);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
    }
}
