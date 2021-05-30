package br.com.conductor.cadastros.dto.response;

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
public class VendaResponse {
    private Long id;
}
