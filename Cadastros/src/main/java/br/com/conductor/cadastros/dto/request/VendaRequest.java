package br.com.conductor.cadastros.dto.request;

import br.com.conductor.cadastros.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaRequest {
    private BigDecimal valor;
    private int parcelas;
    private Long idCliente;
}
