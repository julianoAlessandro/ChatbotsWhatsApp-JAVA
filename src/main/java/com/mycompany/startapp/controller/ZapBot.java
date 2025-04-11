package com.mycompany.startapp.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class ZapBot {

    private final WebDriver driver;

    public ZapBot(WebDriver driver) {  
        this.driver = driver;
    }

    public void enviarMensagem(String contato, String mensagem) {
           WebDriverWait wait = new WebDriverWait(driver, 20); // <-- usa long ao invés de Duration


        try {
            // Passo 1: Buscar o contato
            WebElement campoBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@contenteditable='true'][@data-tab='3']")));
            campoBusca.click();
            Thread.sleep(500);
            campoBusca.sendKeys(contato);
            System.out.println("Contato digitado: " + contato);

            Thread.sleep(3000); // aguarda resultados aparecerem

            // Passo 2: Clicar na conversa
            WebElement conversa = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@title='" + contato + "']")));
            conversa.click();
            System.out.println("Conversa aberta com: " + contato);

            // Passo 3: Digitar a mensagem
           WebElement campoMensagem = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@contenteditable='true' and @data-tab='10']")));
            campoMensagem.click();
            Thread.sleep(500);
            campoMensagem.sendKeys(mensagem);
            System.out.println("Mensagem digitada: " + mensagem);
           
              //encaminhar a mensagem
              campoMensagem.sendKeys(Keys.ENTER);
              Thread.sleep(500);
              System.out.println("Mensagem enviada com sucesso para: " + contato);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar enviar mensagem para: " + contato);
        }
    }
}
