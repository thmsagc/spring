package br.com.conductor.cadastros.resource;

import br.com.conductor.cadastros.dto.request.LancamentoRequest;
import br.com.conductor.cadastros.dto.response.LancamentoResponse;
import br.com.conductor.cadastros.model.Lancamento;
import br.com.conductor.cadastros.service.LancamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lancamentos")
@Api(value = "API Lancamentos")
@CrossOrigin(origins = "*")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    @ApiOperation(value = "Lista de todos os lançamentos.")
    public @ResponseBody
    List<Lancamento> listar(){
        return lancamentoService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna um lançamento de determinado id.")
    public @ResponseBody
    Lancamento getLancamento(@PathVariable Long id){
        return lancamentoService.getByID(id);
    }

    @GetMapping(value = "/mes/{mes}")
    @ApiOperation(value = "Lançamentos do mês.")
    public @ResponseBody
    List<Lancamento> lancamentosDoMes(@PathVariable int mes){
        return lancamentoService.getByMes(mes);
    }

    @PostMapping
    @ApiOperation(value = "Cadastro de um lançamento.")
    public @ResponseBody
    LancamentoResponse cadastrar(LancamentoRequest lancamento){
        return lancamentoService.salvar(lancamento);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remoção de um lancamento.")
    public @ResponseBody void deletar(@PathVariable Long id) throws Exception {
        lancamentoService.deletar(id);
    }
}
