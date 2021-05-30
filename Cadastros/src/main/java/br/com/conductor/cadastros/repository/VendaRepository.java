package br.com.conductor.cadastros.repository;

import br.com.conductor.cadastros.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByCliente_DiaVencimento(int diaVencimento);
}
