package br.com.conductor.cadastros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {
    private String nome;
    private String cpf;
    private int diaVencimento;
    private int diaCorte;
}


