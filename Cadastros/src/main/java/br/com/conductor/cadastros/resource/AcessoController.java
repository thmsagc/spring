package br.com.conductor.cadastros.resource;

import br.com.conductor.cadastros.jwt.JwtUtil;
import br.com.conductor.cadastros.users.AutenticadorRequest;
import br.com.conductor.cadastros.users.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Geração de Token de acesso")
@CrossOrigin(origins = "*")
public class AcessoController {

    private UsuarioService usuarioService;
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AcessoController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String index(){
        return "Bem vindo a API de Cadastros.";
    }

    @RequestMapping(value = "/acesso", method = RequestMethod.POST)
    @ApiOperation(value = "Geração de token de acesso a partir de um usuário.")
    private @ResponseBody String gerarToken(@RequestBody AutenticadorRequest autenticadorRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autenticadorRequest.getNome(), autenticadorRequest.getSenha())
            );
        } catch (Exception exception) {
            throw new Exception("Usuário ou senha não corresponde!");
        }
        return jwtUtil.generateToken(autenticadorRequest.getNome());
    }

}
