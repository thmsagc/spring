package br.com.conductor.cadastros.dto.request;

import br.com.conductor.cadastros.model.Venda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LancamentoRequest {
    private int parcela;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private Venda venda;
}
