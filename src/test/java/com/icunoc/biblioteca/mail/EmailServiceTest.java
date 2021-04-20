package com.icunoc.biblioteca.mail;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class EmailServiceTest {

    //Mock que nos servira
    @Autowired
    JavaMailSender senderMock = Mockito.mock(JavaMailSender.class);

    @Autowired
    EmailService emailService = new EmailService();

    @BeforeEach
    void setUp() {
        //un mock de MimeMessaje
        MimeMessage mimeMessageMock = Mockito.mock(MimeMessage.class);

        //mocks de metodos a retornar
        Mockito.when(senderMock.createMimeMessage()).thenReturn(mimeMessageMock);

    }

    @Test
    void sendEmailSuccess() { //Escenario 1: Va bien y se envia el mail
        //Arrage
        boolean resultEsperado = true;
        boolean result ;
        emailService.setJavaMailerMock(senderMock);
        EmailBody emailBody = new EmailBody();
        //spy que hara el trabajo sucio para que no truene
        EmailBody spy = Mockito.spy(emailBody);
        spy.setEmail("mail");
        spy.setSubject("subject");
        spy.setContent("content");

        //Act
        result = emailService.sendEmail(spy);


        //Assert
        Assertions.assertEquals(resultEsperado, result);
    }

}