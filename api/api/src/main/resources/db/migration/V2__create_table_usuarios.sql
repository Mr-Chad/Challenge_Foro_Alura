CREATE TABLE usuarios(
                           id         BIGINT PRIMARY KEY AUTO_INCREMENT,
                           nombre     VARCHAR(255) NOT NULL UNIQUE,
                           email      VARCHAR(255) NOT NULL UNIQUE,
                           contrasena VARCHAR(255) NOT NULL
  );