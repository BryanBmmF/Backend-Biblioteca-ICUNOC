package com.icunoc.biblioteca.constants;

public class AppConstants {

    private AppConstants(){
        //Este es un contructor vacio
    }

    public static final long MAX_USER_TOKEN_INACTIVITY_MIN = 5;
    public static final String RESPONSE_BODY_MESSAGE_KEY = "message";
    public static final String AUTH_TOKEN_NAME = "authToken";
    public static final String NOT_AUTHORIZE = "NO_AUTORIZADO";
    public static final String URL_APP = "http://localhost:4200/";

    //Estados de Prestamos
    public static final String ESTADO_ACTIVO="ACTIVO";
    public static final String ESTADO_FINALIZADO="FINALIZADO";
    public static final String ESTADO_EXPIRADO="EXPIRADO";
    public static final String ESTADO_RESERVADO="RESERVADO";


}
