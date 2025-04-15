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

      
       options.addArguments("--window-position=0,1000");
       options.addArguments("--remote-allow-origins=*");

        // Garante que esse diretório existe e está acessível (ou cria antes de rodar)
        options.addArguments("--user-data-dir=C:/zap-data");

        WebDriver driver = new ChromeDriver(options);

        try {
            System.out.println("Abrindo WhatsApp Web...");
            driver.get("https://web.whatsapp.com");

           
            Thread.sleep(15000);

            ZapBot bot = new ZapBot(driver);
            String caminhoArquivo = "C:\\Users\\Juliano\\Documents\\NetBeansProjects\\StartApp\\Arquivos\\documento.pdf";
            bot.enviarMensagem("Juliano", "Bom dia luis  o vencimento do sistema vence dia 17/04 abaixo segue o boleto para pagamento !!!" + System.currentTimeMillis());
            bot.encaminharPDF(caminhoArquivo);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // Descomente se quiser fechar o navegador após o envio
        }
    }
}
