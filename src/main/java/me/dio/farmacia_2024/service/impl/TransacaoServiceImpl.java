package me.dio.farmacia_2024.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Transacao;
import me.dio.farmacia_2024.domain.repository.TransacaoRepository;
import me.dio.farmacia_2024.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Override
    public void registrarTransacao(String nome, String nomeProduto, String codigoDeBarras, int quantidade, String tipoTransacao) {
        
        Transacao transacao = new Transacao();
        transacao.setNome(nome);
        transacao.setDataMovimentacao(LocalDate.now());
        transacao.setNomeProduto(nomeProduto);
        transacao.setCodigoDeBarras(codigoDeBarras);
        transacao.setQuantidade(quantidade);
        transacao.setTipoTransacao(tipoTransacao);
        transacaoRepository.save(transacao);
    }

    @Override
    public List<Transacao> visualizarTransacoes() {
        return transacaoRepository.findAll();
    }

    @Override
    public List<Transacao> visualizarTransacoesDoFarmaceutico(String nome) {
        return transacaoRepository.findByNome(nome);
    }

}