package br.com.conductor.cadastros.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticadorRequest {
    private String nome;
    private String senha;
}
