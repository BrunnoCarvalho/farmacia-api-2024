package me.dio.farmacia_2024.service;

import java.time.LocalDate;
import java.util.List;

import me.dio.farmacia_2024.domain.model.Lote;

public interface LoteService {

    Lote adicionarLoteComProduto(Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf);

    void adicionarQuantidadeProduto(Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf);

    void removerLote(Long estoqueId, String numeroLote, String cpf);

    void removerQuantidadeProduto (Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf);

    int verificarQuantiaDeUmLote(Long estoqueId, String numeroLote);

    List<Lote> verificarLotes(Long estoqueId);

}
