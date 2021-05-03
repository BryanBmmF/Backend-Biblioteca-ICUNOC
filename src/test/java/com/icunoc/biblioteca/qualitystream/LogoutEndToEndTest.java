package com.icunoc.biblioteca.qualitystream;

import org.junit.jupiter.api.AfterEach;
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

public class LogoutEndToEndTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        //Especificando el webdriver a utilizar y la ruta donde se encuentra
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        //Nuevo objeto ChromeDriver y maximizando la ventana abierta
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //La URL donde se hara el test end to end
        driver.get(URL_APP+"login/");

    }

    @Test
    public void LoginAndLogoutAdminTestSuccess(){
        //Arrage
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement inputPass = driver.findElement(By.name("password"));
        inputPass.clear();
        WebElement button = driver.findElement(By.name("login"));

        //Act
        inputUser.sendKeys("admin");
        inputPass.sendKeys("password");
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos muestre la nueva pagina
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que nos redirija a la nueva url de un user admin
        wait.until(ExpectedConditions.urlToBe(URL_APP+"usuarios"));

        //Media vez se completa la espera de maximo 5 segundos para que redirija a la nueva URL declaramos un elemento que esperamos en la nueva vista
        By logoutBotton = By.name("logout");
        WebElement elementNewView = driver.findElement(logoutBotton);

        //hacemos el logout correspondiente con el el boton declarado
        elementNewView.click();

        //hacemos otra espera para el logout hasta que vuelva a la pantalla de login
        wait.until(ExpectedConditions.urlToBe(URL_APP+"login"));

        //Assert para saber que ya nos deslogueamos y estamos actualmente en la pantalla de login
        Assertions.assertEquals(URL_APP+"login", driver.getCurrentUrl());

    }

    @AfterEach
    void tearDown() {
        //cerrando la instancia del webdriver
        driver.quit();

    }
}
