package br.com.conductor.cadastros.mappers;

import br.com.conductor.cadastros.dto.request.LancamentoRequest;
import br.com.conductor.cadastros.dto.response.LancamentoResponse;
import br.com.conductor.cadastros.model.Lancamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LancamentoMapper extends GenericMapper<Lancamento, LancamentoRequest, LancamentoResponse>{
    Lancamento pedidoParaEntidade(LancamentoRequest u);
}
