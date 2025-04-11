package com.mycompany.startapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class, args);
        
    }

    @Bean
    public WebDriver webDriver() {
        log.info("Inicializando o Selenium WebDriver com WebDriverManager...");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false); // true = sem interface gráfica

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://web.whatsapp.com/");

        // Manter o navegador aberto por um tempo (16 minutos)
        try {
            Thread.sleep(1000 * 60 * 16);
        } catch (InterruptedException e) {
            log.error("Thread interrompida", e);
            Thread.currentThread().interrupt();
        }

        return driver;
    }
}
