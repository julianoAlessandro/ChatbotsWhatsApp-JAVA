package com.mycompany.startapp.model;

import java.util.Objects;
import java.util.Set;

public class Mensagem {
    private Set<String> contatos;
    private String conteudo;

    public Mensagem() {
    }

    public Mensagem(Set<String> contatos, String conteudo) {
        this.contatos = contatos;
        this.conteudo = conteudo;
    }

    public Set<String> getContatos() {
        return contatos;
    }

    public void setContatos(Set<String> contatos) {
        this.contatos = contatos;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "contatos=" + contatos +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;
        Mensagem mensagem = (Mensagem) o;
        return Objects.equals(contatos, mensagem.contatos) &&
                Objects.equals(conteudo, mensagem.conteudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contatos, conteudo);
    }
}
