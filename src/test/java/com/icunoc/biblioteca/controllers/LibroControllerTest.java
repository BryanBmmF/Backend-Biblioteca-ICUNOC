package com.icunoc.biblioteca.controllers;

import com.icunoc.biblioteca.dto.LibroDto;
import com.icunoc.biblioteca.enums.Idioma;
import com.icunoc.biblioteca.models.Libro;
import com.icunoc.biblioteca.services.LibrosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.*;

class LibroControllerTest {

    @Autowired
    LibrosService service = Mockito.mock(LibrosService.class);

    @Autowired
    LibroController libroController = new LibroController();

    //libroDto Mock
    @Autowired
    LibroDto libroDto =  Mockito.mock(LibroDto.class);
    //Constantes de prueba
    private static final byte[] BYTES_IMAGEN = "bytes".getBytes();
    private static final String CODIGO_TEST = "12345";
    private static final String CODIGO_TEST_2 = "12344";
    private static final String NOMBRE_TEST = "Nombre";

    @BeforeEach
    void setUp() {
        //declaramos un libro por defecto
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = BYTES_IMAGEN;
        Libro mockLibro =  new Libro();
        mockLibro.setIdLibro(1);
        mockLibro.setCodigo(CODIGO_TEST);
        mockLibro.setNombre(NOMBRE_TEST);
        mockLibro.setAutor("Autor");
        mockLibro.setStock(10);
        mockLibro.setEdicion(1);
        mockLibro.setFechaPublicacion(today);
        mockLibro.setIdioma(Idioma.INGLES);
        mockLibro.setPathImagen(imagenBytes);
        List<Libro> miListMock = Arrays.asList(mockLibro);

        //lo que retona mockito
        //una lista de libros
        Mockito.when(service.list()).thenReturn(miListMock);
        //una lista de libros filtrada
        Mockito.when(service.getByBusqueda(NOMBRE_TEST)).thenReturn(miListMock);
        //un libro basado en un id
        Mockito.when(service.getOne(1)).thenReturn(Optional.of(mockLibro));
        //un libro basado en su codigo
        Mockito.when(service.getByCodigo(CODIGO_TEST)).thenReturn(Optional.of(mockLibro));
        //si existe un id
        Mockito.when(service.existsById(1)).thenReturn(true);
        //si no existe un libro segun su id
        Mockito.when(service.existsById(2)).thenReturn(false);
        //si existe un codigo
        Mockito.when(service.existsByCodigo(CODIGO_TEST)).thenReturn(true);
        //si no existe un libro segun su codigo
        Mockito.when(service.existsByCodigo(CODIGO_TEST_2)).thenReturn(false);

    }

    @Test
    void listarLibrosPorBusqueda() {
        //Arrange
        libroController.setService(service);
        ResponseEntity<List<Libro>> responseServicio;
        //Act
        responseServicio = libroController.listarLibrosPorBusqueda(NOMBRE_TEST);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    @Test
    void uploadImage() throws IOException {
        //Act
        libroController.uploadImage(new MockMultipartFile("foo", "../foo.txt",
                MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
    }

    @Test
    void listarLibro() {
        libroController.setService(service);
        ResponseEntity<List<Libro>> responseServicio;
        responseServicio = libroController.listarLibro();
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //TEST PARA EL METODO getById (tanto valido como no valido)
    @Test
    void getByIdNoExist(){
        //Arrage
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        //Act
        //si no existe el libro
        service.existsById(2);
        //se retona como respuesta un error 404 con un mensaje
        responseServicio = libroController.getById(2);
        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void getByIdExist() {
        //escenario 1 exist id
        //Arrage
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        //Act
        //se evalua primero que exista el libro
        service.existsById(1);
        //se manda el libro como respuesta
        responseServicio = libroController.getById(1);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //TEST PARA EL METODO getByNombre (que viene siendo el codigo del libro)(tanto valido como no valido)
    @Test
    void getByCodigoExists(){
        //Arrage
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;

        //Act
        service.existsByCodigo(CODIGO_TEST);
        responseServicio = libroController.getByNombre(CODIGO_TEST);

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());

    }
    @Test
    void getByCodigoNoExists(){
        //Arrage
        libroController.setService(service);
        ResponseEntity<Libro> responseServicio;
        //Act
        service.existsByCodigo(CODIGO_TEST_2);
        responseServicio = libroController.getByNombre(CODIGO_TEST_2);

        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());

    }

    //TEST para el metodo create()
        //caso 1, create con campos en blanco
    @Test
    void createCamposVacios() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        responseServicio = libroController.create(libroDto);
        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    //caso 2, create con campos en blanco
    @Test
    void createLibroExistente() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = BYTES_IMAGEN;
        ///Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        //Act
        LibroDto libroDto1 = new LibroDto();
        libroDto1.setCodigo(CODIGO_TEST);//provocamos el fallo
        libroDto1.setNombre("Nombre fallo");
        libroDto1.setAutor("Autor fallo");
        libroDto1.setStock(100);
        libroDto1.setEdicion(100);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.INGLES);
        libroDto1.setPathImagen(imagenBytes);
        service.existsByCodigo(libroDto1.getCodigo());
        responseServicio = libroController.create(libroDto1);
        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
        //caso 3, create perfecto
    @Test
    void createCorrecto() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = BYTES_IMAGEN;
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        LibroDto libroDto1 = new LibroDto();
        libroDto1.setId(1);
        libroDto1.setAutor("Autor1");
        libroDto1.setCodigo("Codigo1");
        libroDto1.setEdicion(1);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.INGLES);
        libroDto1.setNombre("Pruebatest1");
        libroDto1.setPathImagen(imagenBytes);
        libroDto1.setStock(1);

        responseServicio = libroController.create(libroDto1);

        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //TEST para el metodo update()
        //caso 1, update con campos en blanco
    @Test
    void updateCamposVacios() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        //Act
        //el fallo se autoprovoca porque el userDto mock es falso
        responseServicio = libroController.update(1,libroDto);
        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateLibroExistente() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        //Act
        //provocamo que ya exista el id para que falle y no haga el update
        service.existsById(1);
        responseServicio = libroController.update(2,libroDto);
        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
    @Test
    void updateCorrecto() {
        Calendar today = Calendar.getInstance();
        byte[] imagenBytes = BYTES_IMAGEN;
        libroController.setService(service);
        ResponseEntity<?> responseServicio;

        LibroDto libroDto1 = new LibroDto();
        libroDto1.setAutor("Autor12");
        libroDto1.setCodigo("Codigo12");
        libroDto1.setEdicion(2);
        libroDto1.setFechaPublicacion(today);
        libroDto1.setIdioma(Idioma.ESPAÑOL);
        libroDto1.setNombre("Pruebatest12");
        libroDto1.setPathImagen(imagenBytes);
        libroDto1.setStock(12);

        Libro libro = service.getOne(1).get();
        libro.setAutor(libroDto1.getAutor());
        libro.setCodigo(libroDto1.getCodigo());
        libro.setEdicion(libroDto1.getEdicion());
        libro.setFechaPublicacion(libroDto1.getFechaPublicacion());
        libro.setIdioma(libroDto1.getIdioma());
        libro.setNombre(libroDto1.getNombre());
        libro.setPathImagen(libroDto1.getPathImagen());
        libro.setStock(libroDto1.getStock());

        responseServicio = libroController.update(1, libroDto1);

        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }

    //TEST para el metodo delete()
        //caso1, no existe el libro que se quiere eliminar.
    @Test
    void deleteIncorrecto() {
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        //Act
        service.existsById(2);
        responseServicio = libroController.delete(2);
        //Assert
        Assertions.assertEquals(404, responseServicio.getStatusCodeValue());
    }
        // caso2, delete correcto
    @Test
    void deleteCorercto() { //
        //Arrage
        libroController.setService(service);
        ResponseEntity<?> responseServicio;
        //Act
        service.existsById(1);
        responseServicio = libroController.delete(1);
        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
    }
}
