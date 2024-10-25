package me.dio.farmacia_2024.service;

import java.util.List;

import me.dio.farmacia_2024.domain.model.Transacao;

public interface TransacaoService {

    void registrarTransacao(String nome, String nomeProduto, String codigoDeBarras, int quantidade, String tipoTransacao);

    List<Transacao> visualizarTransacoesDoFarmaceutico(String cpf);

    public List<Transacao> visualizarTransacoes();

}
