package br.com.conductor.cadastros.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private boolean admin;

    public Usuario(String nome, String senha, Boolean admin) {
        this.nome = nome;
        this.senha = senha;
        this.admin = admin;
    }
}
