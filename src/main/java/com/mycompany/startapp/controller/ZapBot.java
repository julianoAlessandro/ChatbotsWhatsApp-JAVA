package com.mycompany.startapp.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ZapBot {

    private final WebDriver driver; //driver que garante a conexao com o chrome

    public ZapBot(WebDriver driver) {  
        this.driver = driver; // referencia que esse atributo faz parte dessa classe
    }
    public void enviarMensagem(String contato, String mensagem) {
           WebDriverWait wait = new WebDriverWait(driver, 40); // <-- usa long ao invés de Duration
         
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
            Thread.sleep(700);
            campoMensagem.sendKeys(mensagem);
            System.out.println("Mensagem digitada: " + mensagem);
           
              //encaminhar a mensagem
              campoMensagem.sendKeys(Keys.ENTER);
              Thread.sleep(500);
              System.out.println("Mensagem enviada com sucesso para: " + contato);
           //--------------encaminhar arquivo pdf------------------------
           
          // Passo 1: Clicar no clipe
     

        // Espera o rodapé aparecer primeiro
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));

       // Só então tenta pegar o botão do clipe
       WebElement clipButton = wait.until(ExpectedConditions.elementToBeClickable(
       By.cssSelector("footer button span[data-icon='clip']")
       ));
      clipButton.click();
      System.out.println("Botão de anexo clicado com sucesso.");

       // Passo 2: Upload de documento
          String caminho = "C:\\Users\\Juliano\\Documents\\NetBeansProjects\\StartApp\\Arquivos\\documento.pdf";
         WebElement documentInput = wait.until(ExpectedConditions.presenceOfElementLocated(
          By.cssSelector("By.xpath(\"//footer//button//span[@data-icon='clip']\")")));
          documentInput.sendKeys(caminho); // Caminho do arquivo local
          System.out.println("Procurando o arquivo no local de destino");

        // Passo 3: Esperar botão de enviar aparecer
       WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(
       By.xpath("//span[@data-icon='send']")));
       sendBtn.click();            
       System.out.println("Arquivo enviado com sucesso");   
             
          

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar enviar mensagem para: " + contato);
        }
    }
}
