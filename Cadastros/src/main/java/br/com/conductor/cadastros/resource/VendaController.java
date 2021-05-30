package br.com.conductor.cadastros.resource;
import br.com.conductor.cadastros.dto.request.VendaRequest;
import br.com.conductor.cadastros.dto.response.VendaResponse;
import br.com.conductor.cadastros.model.Venda;
import br.com.conductor.cadastros.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vendas")
@Api(value = "API Vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    @ApiOperation(value = "Lista de todos as vendas.")
    public @ResponseBody
    List<Venda> listar(){
        return vendaService.getAll();
    }

    @PostMapping
    @ApiOperation(value = "Cadastro de uma venda.")
    public @ResponseBody
    VendaResponse cadastrar(@RequestBody VendaRequest venda){
        return vendaService.cadastrar(venda);
    }

    @GetMapping(value = "/dia/{dia}")
    @ApiOperation(value = "Lançamentos do mês.")
    public @ResponseBody
    List<Venda> lancamentosDoMes(@PathVariable int dia){
        return vendaService.getByDia(dia);
    }

    @PostMapping(value="/{id}")
    @ApiOperation(value = "Atualização de uma venda.")
    public @ResponseBody void atualizar(@PathVariable Long id, @RequestBody VendaRequest venda){
        vendaService.atualizar(id, venda);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation(value = "Remoção de um cliente.")
    public @ResponseBody void deletar(@PathVariable Long id){
        vendaService.deletar(id);
    }
}
