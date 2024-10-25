package me.dio.farmacia_2024.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Estoque;
import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.domain.model.Lote;
import me.dio.farmacia_2024.domain.model.Produto;
import me.dio.farmacia_2024.domain.repository.EstoqueRepository;
import me.dio.farmacia_2024.domain.repository.LoteRepository;
import me.dio.farmacia_2024.service.LoteService;
import me.dio.farmacia_2024.service.TransacaoService;
import me.dio.farmacia_2024.service.ValidacaoService;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    private ValidacaoService validacaoService;

    @Override
    public Lote adicionarLoteComProduto(Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf) {
            
        Estoque estoque = validacaoService.validarEstoque(estoqueId);

        Produto produto = validacaoService.validarProduto(nomeProduto);

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Lote novoLote = registrarLote(numeroLote, quantidade, dataValidade, produto, estoque);

        if (estoque.getLotes() == null) {
            estoque.setLotes(new ArrayList<>());
        }
        estoque.getLotes().add(novoLote);

        loteRepository.save(novoLote);
        estoqueRepository.save(estoque);

        transacaoService.registrarTransacao(farmaceutico.getNome(), produto.getNome(), produto.getCodigoDeBarras(), quantidade, "ENTRADA");

        return novoLote;
    }

    @Override
    public void adicionarQuantidadeProduto(Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf) {
        
        validacaoService.validarEstoque(estoqueId);

        Produto produto = validacaoService.validarProduto(nomeProduto);

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Lote lote = validacaoService.validarLote(numeroLote, estoqueId);

        lote = atualizarQuantidadeAoLote(lote, quantidade, dataValidade);

        loteRepository.saveAndFlush(lote);

        transacaoService.registrarTransacao(farmaceutico.getNome(), produto.getNome(), produto.getCodigoDeBarras(), quantidade, "ENTRADA");

    }

    @Override
    public void removerQuantidadeProduto(Long estoqueId, String numeroLote, int quantidade, LocalDate dataValidade, String nomeProduto, String cpf) {
        
        validacaoService.validarEstoque(estoqueId);

        Produto produto = validacaoService.validarProduto(nomeProduto);

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Lote lote = validacaoService.validarLote(numeroLote, estoqueId);

        lote = atualizarQuantidadeAoLote(lote, -quantidade, dataValidade);

        loteRepository.saveAndFlush(lote);

        transacaoService.registrarTransacao(farmaceutico.getNome(), produto.getNome(), produto.getCodigoDeBarras(), quantidade, "SAÍDA");
    }

    @Override
    public void removerLote(Long estoqueId, String numeroLote, String cpf) {
        
        validacaoService.validarEstoque(estoqueId);

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Lote lote = validacaoService.validarLote(numeroLote, estoqueId);

        loteRepository.delete(lote);

        transacaoService.registrarTransacao(farmaceutico.getNome(), "","", 0, "REMOÇÃO DO LOTE");

    }

    @Override
    public int verificarQuantiaDeUmLote(Long estoqueId, String numeroLote) {
        
        validacaoService.validarEstoque(estoqueId);

        Lote lote = loteRepository.findByNumeroLoteAndEstoqueId(numeroLote, estoqueId)
                .orElseThrow(() -> new IllegalArgumentException("Lote não encontrado com o número: " + numeroLote + " no estoque ID: " + estoqueId));

        return lote.getQuantidade();
    }

    private Lote registrarLote(String numeroLote, int quantidade, LocalDate dataValidade, Produto produto, Estoque estoque) {

        Lote novoLote = new Lote();
        novoLote.setNumeroLote(numeroLote);
        novoLote.setQuantidade(quantidade);
        novoLote.setDataValidade(dataValidade);
        novoLote.setProduto(produto);
        novoLote.setEstoque(estoque);

        return novoLote;

    }

    private Lote atualizarQuantidadeAoLote(Lote lote, int quantidade, LocalDate dataValidade) {

        validarDataValidade(lote, dataValidade);

        int novaQuantidade = lote.getQuantidade() + quantidade;
        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("Quantidade a remover excede a quantidade disponível");
        }
        
        lote.setQuantidade(novaQuantidade);
        return lote;

    }

    private void validarDataValidade(Lote lote, LocalDate dataValidade) {

        if (!dataValidade.equals(lote.getDataValidade())) {
            throw new IllegalArgumentException("Data de validade inválida");
        }

    }

    @Override
    public List<Lote> verificarLotes(Long estoqueId) {
        
        validacaoService.validarEstoque(estoqueId);

        return loteRepository.findByEstoqueId(estoqueId);
    }

}
