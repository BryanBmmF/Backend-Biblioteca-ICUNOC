package com.icunoc.biblioteca.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;

class EmailRestTest {

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

        Mockito.when(emailPortMock.sendEmail(any(EmailBody.class))).thenReturn(true);


    }

    @Test
    void sendEmailSuccess() { //Escenario 1, envio correcto del email
        //Arrage
        emailRest.setService(emailPortMock);
        //declaramos el response
        ResponseEntity<?> responseServicio;

        //Act
        EmailBody emailBody = new EmailBody();
        EmailBody spy = Mockito.spy(emailBody);
        spy.setEmail("bryan.bmmf@gmail.com");
        spy.setContent("content");
        spy.setSubject("subject");
        responseServicio = emailRest.sendEmail(spy);

        //verificamos que efectivamente sean estos los datos del spy
        Mockito.verify(spy).setEmail("bryan.bmmf@gmail.com");
        Mockito.verify(spy).setSubject("subject");
        Mockito.verify(spy).setContent("content");

        //Assert
        Assertions.assertEquals(200, responseServicio.getStatusCodeValue());
        Assertions.assertEquals("EmailBody [email="+spy.getEmail()+", content="+spy.getContent()+", subject="+spy.getSubject()+"]", spy.toString());

    }

    @Test
    void sendEmailFail() { //Escenario 2, envio incorrecto del email
        //Arrage
        emailRest.setService(emailPortMock);
        //declaramos el response
        ResponseEntity<?> responseServicio;

        //Act
        responseServicio = emailRest.sendEmail(any(EmailBody.class));

        //Assert
        Assertions.assertEquals(400, responseServicio.getStatusCodeValue());

    }
}