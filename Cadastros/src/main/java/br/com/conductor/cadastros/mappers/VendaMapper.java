package br.com.conductor.cadastros.mappers;

import br.com.conductor.cadastros.dto.request.VendaRequest;
import br.com.conductor.cadastros.dto.response.VendaResponse;
import br.com.conductor.cadastros.model.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendaMapper extends GenericMapper<Venda, VendaRequest, VendaResponse>{
    @Mapping(source = "idCliente", target = "cliente.id")
    Venda pedidoParaEntidade(VendaRequest u);
}
