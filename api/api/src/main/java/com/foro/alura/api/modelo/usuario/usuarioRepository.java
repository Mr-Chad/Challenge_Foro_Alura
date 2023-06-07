package com.foro.alura.api.modelo.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String username);


}
