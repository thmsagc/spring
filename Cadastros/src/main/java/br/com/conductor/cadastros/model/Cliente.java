package br.com.conductor.cadastros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dia_vencimento")
    private int diaVencimento;

    @Column(name = "dia_corte")
    private int diaCorte;

    @JsonIgnoreProperties({"cliente"})
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;
}
