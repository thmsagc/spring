package br.com.conductor.cadastros.repository;

import br.com.conductor.cadastros.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByDataVencimentoBetween(final LocalDate inicio, final LocalDate fim);
}
