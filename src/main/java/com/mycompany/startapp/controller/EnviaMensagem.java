package com.mycompany.startapp.controller;

import com.mycompany.startapp.model.Mensagem;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/zap-zap")  //endpoint endereço da aplicação
@Slf4j
public class EnviaMensagem {

    @Autowired
    private WebDriver webDriver;

    @PostMapping
    public void receberMensagem(@RequestBody Mensagem mensagem) {
        if (mensagem.getContatos() == null || mensagem.getContatos().isEmpty()) {
            log.warn("Lista de contatos vazia.");
            return;
        }

        // Compatível com Java 8
        if (mensagem.getConteudo() == null || mensagem.getConteudo().trim().isEmpty()) {
            log.warn("Mensagem vazia.");
            return;
        }

        for (String contato : mensagem.getContatos()) {
            try {
                WebElement elementoContato = findContato(contato);
                elementoContato.click();

                WebElement caixaMensagem = findCaixaTexto();
                caixaMensagem.sendKeys(mensagem.getConteudo());
                caixaMensagem.sendKeys(Keys.RETURN);

                log.info("Mensagem enviada para {}", contato);
            } catch (Exception e) {
                log.error("Não foi possível enviar a mensagem para o contato {}", contato, e);
            }
        }
    }

    /**
     * Localiza o contato na lista do WhatsApp Web.
     */
    private WebElement findContato(String nomeContato) {
        String xPathContato = "//*[@id=\"pane-side\"]//span[@title='" + nomeContato + "']";
        log.info("Procurando contato: " + nomeContato);
        return webDriver.findElement(By.xpath(xPathContato));
    }

    /**
     * Localiza a caixa de texto da conversa.
     */
    private WebElement findCaixaTexto() {
        String xPathCaixaTexto = "//*[@id=\"main\"]/footer//*[@role='textbox']";
        return webDriver.findElement(By.xpath(xPathCaixaTexto));
    }
}
