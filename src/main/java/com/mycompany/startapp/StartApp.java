package com.mycompany.startapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
    }

    @Bean
    public WebDriver webDriver() {
        System.out.println("Inicializando o Selenium WebDriver manualmente...");

        // Aponta diretamente para o chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Juliano\\Desktop\\DriverGoogle\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false); // true = sem interface gráfica

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://web.whatsapp.com/");

        System.out.println("WebDriver iniciado e navegado para o WhatsApp Web.");

        return driver;
    }
}
