package com.foro.alura.api.controllers;

import com.foro.alura.api.infra.errores.TratadorDeErrores;
import com.foro.alura.api.modelo.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/foro/usuario")
public class UsuarioController {
    @Autowired
    private usuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping
    public ResponseEntity<RespuestaUsuario> registrarUsuario(@RequestBody @Valid RegistroUsuario registroUsuario,
                                                             UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = new Usuario(registroUsuario);
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuarioRepository.save(usuario);
        RespuestaUsuario datosRespuestaUsuario = new RespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> RetornarDatosUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new RespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new RespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuarioRepository.delete(usuario);
            return ResponseEntity.noContent().build();
        }
        return new TratadorDeErrores().tratarError404();
    }
}
