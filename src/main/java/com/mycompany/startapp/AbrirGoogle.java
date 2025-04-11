package com.mycompany.startapp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbrirGoogle {
    public static void main(String[] args) {
        // Caminho do chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Juliano\\Desktop\\DriverGoogle\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true); // modo invisível
        options.addArguments("--window-size=1920,1080"); // simula tela cheia
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        System.out.println("Abrindo WhatsApp Web em modo headless...");
        driver.get("https://web.whatsapp.com");

        System.out.println("Título da página: " + driver.getTitle());

        // Espera só pra teste
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
        System.out.println("Chrome encerrado.");
    }
}

