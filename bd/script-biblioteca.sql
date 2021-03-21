DROP DATABASE IF EXISTS BIBLIOTECA;
CREATE DATABASE BIBLIOTECA;

USE BIBLIOTECA;

CREATE TABLE Usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    numeroRegistro VARCHAR(9) NOT NULL,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL

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
    stock INT NOT NULL,
    idCategoria INT NULL,
    FOREIGN KEY(idCategoria) REFERENCES Categoria(id)
);
