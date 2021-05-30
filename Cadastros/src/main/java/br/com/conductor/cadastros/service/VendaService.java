package br.com.conductor.cadastros.service;

import br.com.conductor.cadastros.dto.request.VendaRequest;
import br.com.conductor.cadastros.dto.response.VendaResponse;
import br.com.conductor.cadastros.mappers.VendaMapper;
import br.com.conductor.cadastros.model.Cliente;
import br.com.conductor.cadastros.model.Venda;

import br.com.conductor.cadastros.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaMapper vendaMapper;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ClienteService clienteService;

    public List<Venda> getAll() {
        return vendaRepository.findAll();
    }

    public Venda getByID(Long id) {
        Optional<Venda> procuraVenda = vendaRepository.findById(id);
        return procuraVenda.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Venda> getByDia(int dia){
        return vendaRepository.findByCliente_DiaVencimento(dia);
    }

    public VendaResponse cadastrar(VendaRequest vendaRequest) {
        Venda venda = vendaMapper.pedidoParaEntidade(vendaRequest);
        Cliente cliente = clienteService.getByID(vendaRequest.getIdCliente());
        venda.setCliente(cliente);
        venda = vendaRepository.save(venda);
        lancamentoService.gerarLancamentos(venda);
        return vendaMapper.entidadeParaResposta(venda);
    }

    public List<VendaResponse> salvarTodos(List<VendaRequest> vendas) {
        return vendaMapper.entidadesParaRespostas(vendaRepository.saveAll(vendaMapper.pedidosParaEntidades(vendas)));
    }

    public VendaResponse atualizar(Long id, VendaRequest atualizado) {
        Venda venda = vendaRepository.getOne(id);
        venda.setValor(atualizado.getValor());
        venda.setCliente(clienteService.getByID(atualizado.getIdCliente()));
        return vendaMapper.entidadeParaResposta(venda);
    }

    public void deletar(Long id) {
        Optional<Venda> procuraVenda = vendaRepository.findById(id);
        vendaRepository.delete(procuraVenda.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
