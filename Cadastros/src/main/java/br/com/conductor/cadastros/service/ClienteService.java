package br.com.conductor.cadastros.service;

import br.com.conductor.cadastros.dto.request.ClienteRequest;
import br.com.conductor.cadastros.dto.response.ClienteResponse;
import br.com.conductor.cadastros.mappers.ClienteMapper;
import br.com.conductor.cadastros.model.Cliente;
import br.com.conductor.cadastros.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    /*public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }*/

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getByID(Long id) {
        Optional<Cliente> procuraCliente = clienteRepository.findById(id);
        return procuraCliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ClienteResponse salvar(ClienteRequest cliente) {
        return clienteMapper.entidadeParaResposta(clienteRepository.save(clienteMapper.pedidoParaEntidade(cliente)));
    }

    public List<ClienteResponse> salvarTodos(List<ClienteRequest> clientes) {
        return clienteMapper.entidadesParaRespostas(clienteRepository.saveAll(clienteMapper.pedidosParaEntidades(clientes)));
    }

    public ClienteResponse atualizar(Long id, ClienteRequest atualizado) {
        Cliente cliente = clienteRepository.getOne(id);
        cliente.setNome(atualizado.getNome());
        cliente.setCpf(atualizado.getCpf());
        cliente.setDiaVencimento(atualizado.getDiaVencimento());
        return clienteMapper.entidadeParaResposta(cliente);
    }

    public void deletar(ClienteResponse cliente) {
        clienteRepository.delete(clienteMapper.respostaParaEntidade(cliente));
    }

}
