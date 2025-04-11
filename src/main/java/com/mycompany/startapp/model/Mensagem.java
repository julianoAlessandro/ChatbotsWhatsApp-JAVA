package com.mycompany.startapp.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    private Set<String> contatos;
    private String conteudo;
}