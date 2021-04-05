package com.icunoc.biblioteca.mail;

import com.icunoc.biblioteca.dto.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/email")
public class EmailRest {
    @Autowired
    private EmailPort emailPort;

    @PostMapping(value = "/send")
    @ResponseBody
    public ResponseEntity<?> SendEmail(@RequestBody EmailBody emailBody)  {
        if (emailPort.sendEmail(emailBody)){
            return new ResponseEntity(new Mensaje("El correo se envio correctamente !!!"), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje("El correo no se pudo enviar, porfavor intente mas tarde y verifique sus datos"), HttpStatus.BAD_REQUEST);
    }

    //set mock
    public void  setService(EmailPort emailPort){
        this.emailPort = emailPort;
    }
}
