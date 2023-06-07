package com.foro.alura.api.modelo.topico;

import com.foro.alura.api.modelo.curso.Curso;
import com.foro.alura.api.modelo.respuesta.Respuesta;
import com.foro.alura.api.modelo.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensaje;
    @NotNull
    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;
    @NotNull
    @ManyToOne
    private Usuario autor;
    @NotNull
    @ManyToOne
    private Curso curso;
    @NotNull
    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(String titulo, String mensaje, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
    }


    public Topico(RegistroTopico registroTopico) {
        this.titulo = registroTopico.titulo();
        this.mensaje = registroTopico.mensaje();
        this.status = registroTopico.status();
        this.autor = new Usuario(registroTopico.autor());
        this.curso = new Curso(registroTopico.curso());
    }

    public Topico(Long topico) {
        this.id = topico;
    }

    public void actualizarDatos(ActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.estado() != null) {
            this.status = datosActualizarTopico.estado();
        }
        if (datosActualizarTopico.autor() != null) {
            this.autor = new Usuario(datosActualizarTopico.autor());
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = new Curso(datosActualizarTopico.curso());
        }
    }
}
