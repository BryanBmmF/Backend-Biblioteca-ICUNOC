package com.icunoc.biblioteca.pruebas;

public class Calculator {
    /**
     * Metodo de prueba que suma una serie de numeros positivos
     * */
    public int sumPositiveValues(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            if(number>0){
                sum += number;
            }
        }
        return sum;
    }
}
