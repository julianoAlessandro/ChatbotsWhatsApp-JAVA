package com.mycompany.startapp.controller;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class ZapBot {

    private final WebDriver driver; // essse atributo é apenas desta classe e so pode ser acessada pela mesma
    private final WebDriverWait esperar;
    private final Robot atalho;

    public ZapBot(WebDriver driver){ //construtor já recebe uma instancia da classe webdriver logo não preciso instanciar para cada funcao que eu criar pois o método é global
        this.driver = driver;
        this.esperar = new WebDriverWait(driver, 40); // esperar 40s
        Robot temp = null;
        try {
            temp = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            System.out.println("Erro ao inicializar o Robot.");
        }
        this.atalho = temp;
    }

    

    public void enviarMensagem(String contato, String mensagem) {
        try {
            // Passo 1: Buscar o contato
            WebElement campoBusca = esperar.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@contenteditable='true'][@data-tab='3']")));
            campoBusca.click();
            Thread.sleep(500);
            campoBusca.sendKeys(contato);
            System.out.println("Contato digitado: " + contato);
            Thread.sleep(3000);

            // Passo 2: Clicar na conversa
            WebElement conversa = esperar.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@title='" + contato + "']")));
            conversa.click();
            System.out.println("Conversa aberta com: " + contato);

            // Passo 3: Digitar e enviar a mensagem
            WebElement campoMensagem = esperar.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@contenteditable='true' and @data-tab='10']")));
            campoMensagem.click();
            Thread.sleep(700);
            campoMensagem.sendKeys(mensagem);
            System.out.println("Mensagem digitada: " + mensagem);
            campoMensagem.sendKeys(Keys.ENTER);
            Thread.sleep(500);
            System.out.println("Mensagem enviada com sucesso para: " + contato);
          
             } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar enviar mensagem para: " + contato);
        }
    }
    
    public void encaminharPDF(String caminhoArquivo){
        driver.findElement(By.xpath("//*[@id='main']/footer/div[1]/div/span/div/div[1]/div/button/span")).click();
        System.out.println("Clicando no ícone de anexar...");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/span[5]/div/ul/div/div/div[1]/li/div/span")).click();
        System.out.println("Clicando no documento..");
        // Localiza o input de envio de arquivos (geralmente tipo 'file')
        WebElement inputFile = driver.findElement(By.cssSelector("input[type='file']"));
        inputFile.sendKeys(caminhoArquivo);
        System.out.println("PDF carregado.");
        // Aguarda aparecer o botão de envio
          WebElement botaoEnviar = esperar.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[@role='button'][@aria-label='Enviar']") 
          ));
          // Clica para enviar o PDF
        botaoEnviar.click();
        System.out.println("PDF enviado com sucesso!");
        atalho.keyPress(KeyEvent.VK_ESCAPE);
        atalho.keyRelease(KeyEvent.VK_ESCAPE);
        System.out.println("Explorador de arquivo fechado!!");
       }

}
