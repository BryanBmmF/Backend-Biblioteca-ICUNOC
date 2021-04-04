package com.icunoc.biblioteca.mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    //Mock que nos servira
    @Autowired
    JavaMailSender senderMock = Mockito.mock(JavaMailSender.class);

    @Autowired
    EmailService emailService = new EmailService();

    @BeforeEach
    void setUp() {

        //mocks


    }

    @Test
    void sendEmail() {
        //Arrage
        emailService.setJavaMailerMock(senderMock);


        //Act

        //Assert
    }
}