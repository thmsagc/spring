package br.com.conductor.cadastros.resource;

import br.com.conductor.cadastros.dto.request.ClienteRequest;
import br.com.conductor.cadastros.dto.response.ClienteResponse;
import br.com.conductor.cadastros.mappers.ClienteMapper;
import br.com.conductor.cadastros.model.Cliente;
import br.com.conductor.cadastros.repository.ClienteRepository;
import br.com.conductor.cadastros.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
@Api(value = "API Clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @ApiOperation(value = "Lista de todos os clientes.")
    public @ResponseBody List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Retorna um cliente de determinado id.")
    public @ResponseBody
    Cliente getCliente(@PathVariable Long id){
        return clienteService.getByID(id);
    }

    @PostMapping
    @ApiOperation(value = "Cadastro de um cliente.")
    public @ResponseBody
    ClienteResponse cadastrar(@RequestBody ClienteRequest cliente){
        return clienteService.salvar(cliente);
    }

    @PutMapping(value="/{id}")
    @ApiOperation(value = "Atualização de um cliente.")
    public @ResponseBody ClienteResponse atualizar(@PathVariable Long cliente_id, @RequestBody ClienteRequest cliente){
        return clienteService.atualizar(cliente_id, cliente);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation(value = "Remoção de um cliente.")
    public @ResponseBody void deletar(@RequestBody ClienteResponse cliente){
        clienteService.deletar(cliente);
    }
}
