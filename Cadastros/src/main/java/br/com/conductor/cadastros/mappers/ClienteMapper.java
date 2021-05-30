package br.com.conductor.cadastros.mappers;

import br.com.conductor.cadastros.dto.request.ClienteRequest;
import br.com.conductor.cadastros.dto.response.ClienteResponse;
import br.com.conductor.cadastros.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends GenericMapper <Cliente, ClienteRequest, ClienteResponse> {
    Cliente pedidoParaEntidade(ClienteRequest u);
}
