package com.icunoc.biblioteca.qualitystream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginEndToEndTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:4200/login/");

    }

    @Test
    public void LoginTestSuccess(){
        WebElement inputUser = driver.findElement(By.name("user"));
        inputUser.clear();
        WebElement inputPass = driver.findElement(By.name("password"));
        inputPass.clear();

        inputUser.sendKeys("admin");
        inputPass.sendKeys("password");

        //enter key
        inputPass.submit();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assertions.assertEquals("FrontendBibliotecaICUNOC", driver.getTitle());


    }

    @AfterEach
    void tearDown() {
        driver.quit();

    }

}
