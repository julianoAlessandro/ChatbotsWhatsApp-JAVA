package com.mycompany.startapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.mycompany.startapp.controller.ZapBot;

public class StartApp {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Removemos o modo headless para que o navegador seja visível
        options.addArguments("--start-maximized");
        //options.addArguments("--no-sandbox");
        //options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // O "--disable-gpu" pode causar crash em algumas máquinas com headless desativado
        // então vamos comentar por segurança:
        // options.addArguments("--disable-gpu");

        // Garante que esse diretório existe e está acessível (ou cria antes de rodar)
        options.addArguments("--user-data-dir=C:/zap-data");

        WebDriver driver = new ChromeDriver(options);

        try {
            System.out.println("Abrindo WhatsApp Web...");
            driver.get("https://web.whatsapp.com");

            // Espera tempo suficiente pro usuário escanear o QR Code se necessário
            Thread.sleep(15000); // você pode aumentar esse tempo se quiser

            ZapBot bot = new ZapBot(driver);
            bot.enviarMensagem("Ivan", "Olá Ivan! Esta é uma mensagem automática enviada pelo bot");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // Descomente se quiser fechar o navegador após o envio
        }
    }
}
