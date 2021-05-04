package com.icunoc.biblioteca.qualitystream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.icunoc.biblioteca.constants.AppConstants.URL_APP;

public class NuevaReservacionEndToEndTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        //Especificando el webdriver a utilizar y la ruta donde se encuentra
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        //Nuevo objeto ChromeDriver y maximizando la ventana abierta
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //La URL donde se hara el test end to end
        driver.get(URL_APP+"login");
    }

    @Test
    public void NuevoPrestamoMenuButtonTestSuccess(){
        //Arrage
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement inputPass = driver.findElement(By.name("password"));
        inputPass.clear();
        WebElement button = driver.findElement(By.name("login"));

        inputUser.sendKeys("admin");
        inputPass.sendKeys("password");
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos muestre la nueva pagina
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que nos redirija a la nueva url de un user admin
        wait.until(ExpectedConditions.urlToBe(URL_APP+"usuarios"));

        WebElement button1 = driver.findElement(By.name("prestamosMenu"));
        button1.click();

        WebElement button2 = driver.findElement(By.name("nuevoPrestamo"));
        button2.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos muestre la nueva pagina
        WebDriverWait wait2 = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que nos redirija a la nueva url de un user admin
        wait2.until(ExpectedConditions.urlToBe(URL_APP+"identificarLibro"));

        //Media vez se completa la espera de maximo 5 segundos para que redirija a la nueva URL declaramos un elemento que esperamos en la nueva vista
        By cardTittle = By.id("identificarLibro");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card que posee el titulo de la consulta de disponibilidad de libros.
        Assertions.assertEquals("Consultar Disponibilidad de Libro:", elementNewView.getText());

    }

}
