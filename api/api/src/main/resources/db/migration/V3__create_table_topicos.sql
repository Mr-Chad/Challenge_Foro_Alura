CREATE TABLE topicos(
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo         VARCHAR(255) NOT NULL UNIQUE,
    mensaje        VARCHAR(255) NOT NULL UNIQUE,
    fecha_creacion DATETIME     NOT NULL,
    status         VARCHAR(100) NOT NULL,
    autor_id     BIGINT       NOT NULL,
    curso_id       BIGINT       NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios (id) ON DELETE CASCADE ,
    FOREIGN KEY (curso_id) REFERENCES cursos (id) ON DELETE CASCADE
);