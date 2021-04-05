package com.icunoc.biblioteca.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class EmailRestTest {
    //servicio mock de emails
    //@Autowired
    //EmailService emailServiceMock = Mockito.mock(EmailService.class);
    @Autowired
    EmailPort emailPortMock = Mockito.mock(EmailPort.class);

    //EmailBody Mock, no fue necesario
    @Autowired
    EmailBody emailBody = Mockito.mock(EmailBody.class);

    //Obejeto del tipo controller
    @Autowired
    EmailRest emailRest = new EmailRest();

    @BeforeEach
    void setUp() {

        //Opcion 1: Cuando llamen al metodo senEmail del serviceMock retornamos un objeto matchers any porque no nos interesa saber que objeto es
        //y ademas solo hay un unico posible valor a retornar que es true o false
        Mockito.when(emailPortMock.sendEmail(any(EmailBody.class))).thenReturn(true);

        //Opcion 2: aprovechar y de una probar el Pojo pero es necesario crear un objeto real para retornarlo al momento que lo pida el servideMock
        //la opcion 2 no funciona porque lo unico que devuelve el metodo que se mockea es un true

        //EmailBody emailBodyMock = new EmailBody();
        //emailBodyMock.setEmail("bryan.bmmf@gmail.com");
        //emailBodyMock.setSubject("subject");
        //emailBodyMock.setContent("content");
        //Mockito.when(emailPortMock.sendEmail(emailBodyMock)).thenReturn(true);


    }

    @Test
    void sendEmailSuccess() { //Escenario 1, envio correcto del email
        //Arrage
        emailRest.setService(emailPortMock);
        //declaramos el response
        ResponseEntity<?> responseServicio;

        //Act
        //Opcion1: como definimos un matchers en el service enviamos un matchers, pero esto no funciona sin spy
        //responseServicio = emailRest.SendEmail(any(EmailBody.class));
        //Opcion 2: definir el objeto real que se espera: Pero en este caso vamos a definir un spy para que funcione
        EmailBody emailBody = new EmailBody();
        EmailBody spy = Mockito.spy(emailBody);
        spy.setEmail("bryan.bmmf@gmail.com");
        spy.setContent("content");
        spy.setSubject("subject");
        responseServicio = emailRest.SendEmail(spy);
        System.out.println(responseServicio);

        //verificamos que efectivamente sean estos los datos del spy
        Mockito.verify(spy).setEmail("bryan.bmmf@gmail.com");
        Mockito.verify(spy).setSubject("subject");
        Mockito.verify(spy).setContent("content");

        //Assert
        //Aprovechamos y hacemos de una las pruebas de get, aunque se fue con el toString
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
        //Assertions.assertEquals("bryan.bmmf@gmail.com", spy.getEmail());
        //Assertions.assertEquals("subject", spy.getSubject());
        //Assertions.assertEquals("content", spy.getContent());
        Assertions.assertEquals("EmailBody [email="+spy.getEmail()+", content="+spy.getContent()+", subject="+spy.getSubject()+"]", spy.toString());

    }

    @Test
    void sendEmailFail() { //Escenario 2, envio incorrecto del email
        //Arrage
        emailRest.setService(emailPortMock);
        //declaramos el response
        ResponseEntity<?> responseServicio;

        //Act
        //Opcion1: como definimos un matchers en el service enviamos un matchers, pero esto no funciona sin spy, asi que funciona en este escenario de fallo
        responseServicio = emailRest.SendEmail(any(EmailBody.class));

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());

    }
}