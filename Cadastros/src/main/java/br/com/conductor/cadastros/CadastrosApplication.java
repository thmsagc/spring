package br.com.conductor.cadastros;

import br.com.conductor.cadastros.users.Usuario;
import br.com.conductor.cadastros.users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CadastrosApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostConstruct
	public void cadastrarAdmin(){
		String nome = "admin";
		String senha = "admin";

		Usuario consulta = usuarioRepository.findByNome(nome);
		if(consulta == null)
			usuarioRepository.save(new Usuario(nome, senha, true));
	}

	public static void main(String[] args) {
		SpringApplication.run(CadastrosApplication.class, args);
	}

}
