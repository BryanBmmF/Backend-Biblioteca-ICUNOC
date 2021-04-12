DROP DATABASE IF EXISTS BIBLIOTECA;
CREATE DATABASE BIBLIOTECA;

USE BIBLIOTECA;

CREATE TABLE Info_Biblioteca (
	id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    horario VARCHAR(150) NOT NULL,
    diasHabilesPrestamo INT NOT NULL,
    costoDiaMoroso FlOAT NOT NULL,
    costoGeneralPrestamo  FlOAT NOT NULL

);

INSERT INTO Info_Biblioteca VALUES (null,"biblioteca.icunoc@gmail.com", "Calle Rodolfo Robles 29-99, Quezaltenango, Primer nivel Modulo G Ingenieria CUNOC",
									"7873-0000", "Lunea a Viernes de 14:00 a 18:00 hrs, y Sabados de 8:00 a 12:00 hrs", 7, 5.0, 20.0);

CREATE TABLE Usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    numeroRegistro VARCHAR(9) NOT NULL,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    tipo VARCHAR(25) NOT NULL,
    correo VARCHAR(50)

);

CREATE TABLE Rol(
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipoRol VARCHAR(100) UNIQUE NOT NULL

);

CREATE TABLE Autorizacion_Usuario(
     idUsuario INT NOT NULL,
     idRol INT NOT NULL,
     FOREIGN KEY(idUsuario) REFERENCES Usuario(id),
     FOREIGN KEY(idRol) REFERENCES Rol(id)

);

CREATE TABLE Token_Autenticacion(
    id VARCHAR(200) PRIMARY KEY,
    ultimoAcceso DATETIME(6) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY(idUsuario) REFERENCES Usuario(id)
);

CREATE TABLE Categoria(
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(300) NULL,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Libro(
    id INT AUTO_INCREMENT PRIMARY KEY,
    autor VARCHAR(150) NULL,
    codigo VARCHAR(50) UNIQUE NULL,
    edicion INT NOT NULL,
    fechaPublicacion DATE NULL,
    idioma INT NOT NULL,
    nombre VARCHAR(200) NOT NULL,
    pathImagen longblob NULL,
    stock INT NOT NULL
);


CREATE TABLE Prestamo(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    apellido VARCHAR(200) NOT NULL,
    dpi VARCHAR(15) NULL,
    carnet VARCHAR(11) NULL,
    carrera VARCHAR(100) NULL,
    fechaReservacion DATE NULL,
    fechaInicio DATE NULL,
    fechaFin DATE NULL,
    costo DOUBLE NULL,
    estado VARCHAR(20) NOT NULL,
    codigoReservacion VARCHAR(10) NOT NULL,
    mora boolean NULL,
    diasMoroso INT NULL,
    codigoLibro VARCHAR(50) NOT NULL,
    FOREIGN KEY(codigoLibro) REFERENCES Libro(codigo)
);

CREATE TABLE IF NOT EXISTS BIBLIOTECA.asignacion_libro (
    id INT NOT NULL AUTO_INCREMENT,
    idLibro INT NOT NULL,
    idCategoria INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_tag_libro1_idx (idLibro ASC) VISIBLE,
    INDEX fk_tag_categoria1_idx (idCategoria ASC) VISIBLE,
    CONSTRAINT fk_tag_libro1
    FOREIGN KEY (idLibro)
    REFERENCES BIBLIOTECA.Libro (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_tag_categoria1
    FOREIGN KEY (idCategoria)
    REFERENCES BIBLIOTECA.Categoria (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
