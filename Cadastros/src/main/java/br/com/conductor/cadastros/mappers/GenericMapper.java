package br.com.conductor.cadastros.mappers;

import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

public interface GenericMapper <T, U, V>{ //CLASS REQUEST RESPONSE
    U entidadeParaPedido(T t);

    List<U> entidadeParaPedidos(List<T> t);

    List<T> pedidosParaEntidades(List<U> u);

    V entidadeParaResposta(T t);

    List<V> entidadesParaRespostas(List<T> t);

    T respostaParaEntidade(V v);
}
