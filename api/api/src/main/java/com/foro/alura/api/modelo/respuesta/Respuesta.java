package com.foro.alura.api.modelo.respuesta;

import com.foro.alura.api.modelo.topico.Topico;
import com.foro.alura.api.modelo.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String mensaje;
    @ManyToOne
    private Topico topico;
    @CreatedDate
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @ManyToOne
    private Usuario autor;
    @NotNull
    private Boolean solucion = false;

    public Respuesta(RegistroRespuesta registroRespuesta) {
        this.mensaje = registroRespuesta.mensaje();
        this.topico = new Topico(registroRespuesta.topico());
        this.autor = new Usuario(registroRespuesta.autor());
    }

    public void actualizarDatos(ActualizarRespuesta datosActualizarRespuesta) {
        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }
        if (datosActualizarRespuesta.solucion() != null) {
            this.solucion = datosActualizarRespuesta.solucion();
        }
        if (datosActualizarRespuesta.topico() != null) {
            this.topico = new Topico(datosActualizarRespuesta.topico());
        }
        if (datosActualizarRespuesta.autor() != null) {
            this.autor = new Usuario(datosActualizarRespuesta.autor());
        }
    }
}
