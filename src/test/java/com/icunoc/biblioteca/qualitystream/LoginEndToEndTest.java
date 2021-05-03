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

public class LoginEndToEndTest {

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
    public void LoginAdminTestSuccess(){
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
        By cardTittle = By.tagName("mat-card-title");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card llamado Usuarios del sistema
        Assertions.assertEquals("Usuarios del sistema", elementNewView.getText());

    }

    @Test
    public void LoginBibliotecarioTestSuccess(){
        //Arrage
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement inputPass = driver.findElement(By.name("password"));
        inputPass.clear();
        WebElement button = driver.findElement(By.name("login"));

        //Act
        inputUser.sendKeys("user");
        inputPass.sendKeys("password");
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos muestre la nueva pagina
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que nos redirija a la nueva url de un user bibliotecario
        wait.until(ExpectedConditions.urlToBe(URL_APP+"listaCategoriasAdmin"));

        //Media vez se completa la espera de maximo 5 segundos para que redirija a la nueva URL declaramos un elemento que esperamos en la nueva vista
        By cardTittle = By.tagName("mat-card-title");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card llamado Lista de categorias
        Assertions.assertEquals("Lista de categorias", elementNewView.getText());

    }

    @Test
    public void LoginTestWarningOneField(){
        //Arrage
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement button = driver.findElement(By.name("login"));

        //Act
        inputUser.sendKeys("user");
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos devuelva a la misma pantalla
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que la URL sea otra vez login
        wait.until(ExpectedConditions.urlToBe(URL_APP+"login"));

        //Media vez se completa la espera de maximo 5 segundos para que genere la advertencia declaramos un elemento que esperamos
        By cardTittle = By.tagName("h2");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card llamado Lista de categorias
        Assertions.assertEquals("Login de usuarios", elementNewView.getText());

    }

    @Test
    public void LoginTestWarningEmptyField(){
        //Arrage
        WebElement button = driver.findElement(By.name("login"));

        //Act
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos devuelva a la misma pantalla
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que la URL sea otra vez login
        wait.until(ExpectedConditions.urlToBe(URL_APP+"login"));

        //Media vez se completa la espera de maximo 5 segundos para que genere la advertencia declaramos un elemento que esperamos
        By cardTittle = By.tagName("h2");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card llamado Lista de categorias
        Assertions.assertEquals("Login de usuarios", elementNewView.getText());

    }

    @Test
    public void LoginFailTest(){
        //Arrage
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement inputPass = driver.findElement(By.name("password"));
        inputPass.clear();
        WebElement button = driver.findElement(By.name("login"));

        //Act
        inputUser.sendKeys("user_inexistente");
        inputPass.sendKeys("password");
        button.click();

        //hacemos una espera implicita de 5 segundos despues del click al boton por si el servidor se tarda en responder
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Instanciamos la variable de tipo WebDriverWait para gestionar la espera explícita de 5 segundos hasta que nos devuelva a la misma pantalla
        WebDriverWait wait = new WebDriverWait(driver,5);
        // Usamos la espera explícita bajo una condicion esperada, en este caso esperamos hasta que la URL sea otra vez login
        wait.until(ExpectedConditions.urlToBe(URL_APP+"login"));

        //Media vez se completa la espera de maximo 5 segundos para que genere la advertencia declaramos un elemento que esperamos
        By cardTittle = By.tagName("h2");
        WebElement elementNewView = driver.findElement(cardTittle);
        //Assert para saber que ya estamos en un card llamado Lista de categorias
        Assertions.assertEquals("Login de usuarios", elementNewView.getText());

    }

    @AfterEach
    void tearDown() {
        //cerrando la instancia del webdriver
        driver.quit();

    }

}
