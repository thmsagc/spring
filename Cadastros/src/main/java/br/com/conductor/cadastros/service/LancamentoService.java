package br.com.conductor.cadastros.service;

import br.com.conductor.cadastros.dto.request.LancamentoRequest;
import br.com.conductor.cadastros.dto.response.LancamentoResponse;
import br.com.conductor.cadastros.mappers.LancamentoMapper;
import br.com.conductor.cadastros.model.Lancamento;
import br.com.conductor.cadastros.model.Venda;
import br.com.conductor.cadastros.repository.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class LancamentoService{

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoMapper lancamentoMapper;

    public List<Lancamento> getAll() {
        return lancamentoRepository.findAll();
    }

    public Lancamento getByID(Long id){
        Optional<Lancamento> procuraLancamento = lancamentoRepository.findById(id);
        return procuraLancamento.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<Lancamento> getByMes(int mes){
        LocalDate inicio = LocalDate.now().withDayOfMonth(1).withMonth(mes);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());
        List<Lancamento> lancamentos = lancamentoRepository.findByDataVencimentoBetween(inicio, fim);
        return lancamentoRepository.findByDataVencimentoBetween(inicio, fim);
    }

    public LancamentoResponse salvar(LancamentoRequest lancamento) {
        return lancamentoMapper.entidadeParaResposta(lancamentoRepository.save(lancamentoMapper.pedidoParaEntidade(lancamento)));
    }

    public List<LancamentoResponse> salvarTodos(List<LancamentoRequest> lancamentos) {
        return lancamentoMapper.entidadesParaRespostas(lancamentoRepository.saveAll(lancamentoMapper.pedidosParaEntidades(lancamentos)));
    }

    public LancamentoResponse atualizar(Long id, LancamentoRequest atualizado) {
        Lancamento lancamento = lancamentoRepository.getOne(id);
        lancamento.setValor(atualizado.getValor());
        lancamento.setParcela(atualizado.getParcela());
        lancamento.setDataVencimento(atualizado.getDataVencimento());
        return lancamentoMapper.entidadeParaResposta(lancamento);
    }

    public void deletar(Long id) throws Exception {
        Optional<Lancamento> procuraLancamento = lancamentoRepository.findById(id);
        lancamentoRepository.delete(procuraLancamento.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public void gerarLancamentos(Venda venda) {
        BigDecimal valorParcela = venda.getValor().divide(BigDecimal.valueOf(venda.getParcelas()), RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataVencimento = dataAtual.withDayOfMonth(venda.getCliente().getDiaVencimento());;

        if(dataAtual.getDayOfMonth() > venda.getCliente().getDiaVencimento())
            dataVencimento = dataVencimento.plusMonths(1);
        if(dataAtual.getDayOfMonth() > venda.getCliente().getDiaCorte())
            dataVencimento = dataVencimento.plusMonths(1);


        LocalDate finalDataVencimento = dataVencimento;
        IntStream.range(0, venda.getParcelas()).forEach(i -> {
            LancamentoRequest lancamento = new LancamentoRequest(i+1,
                    valorParcela,
                    finalDataVencimento.plusMonths(i),
                    venda);
            lancamentoRepository.save(lancamentoMapper.pedidoParaEntidade(lancamento));
        });
        /*for(int i = 0; i < venda.getParcelas(); i++){
        LancamentoRequest lancamento = new LancamentoRequest(i+1, valorParcela, dataVencimento, venda);
        lancamentoRepository.save(lancamentoMapper.pedidoParaEntidade(lancamento));
        dataVencimento = dataVencimento.plusMonths(1);
        }*/
    }
}
