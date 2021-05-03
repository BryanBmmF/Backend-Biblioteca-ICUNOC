package com.icunoc.biblioteca;

import com.icunoc.biblioteca.constants.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BibliotecaApplicationTests {

    @Test
    void contextLoads() {
        BibliotecaApplication.main(new String[] {});
    }

    @Test
    void constants(){
        //AppConstants appConstants = Mockito.spy(AppConstants.class);
        Assertions.assertEquals(5, AppConstants.MAX_USER_TOKEN_INACTIVITY_MIN);
        Assertions.assertEquals("message", AppConstants.RESPONSE_BODY_MESSAGE_KEY);
        Assertions.assertEquals("authToken", AppConstants.AUTH_TOKEN_NAME);
        Assertions.assertEquals("NO_AUTORIZADO", AppConstants.NOT_AUTHORIZE);
        Assertions.assertEquals("http://localhost:4200/", AppConstants.URL_APP);

    }

}
