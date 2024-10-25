package me.dio.farmacia_2024.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.domain.model.Produto;
import me.dio.farmacia_2024.domain.repository.ProdutoRepository;
import me.dio.farmacia_2024.service.ProdutoService;
import me.dio.farmacia_2024.service.TransacaoService;
import me.dio.farmacia_2024.service.ValidacaoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ValidacaoService validacaoService;

    @Override
    public Produto cadastrarProduto(Produto produto, String cpf) {

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Produto produtoNovo = produtoRepository.save(produto);

        transacaoService.registrarTransacao(farmaceutico.getNome(), produtoNovo.getNome(), produtoNovo.getCodigoDeBarras(), 0, "PRODUTO NOVO");

        return produtoNovo;
    }

    @Override
    public void removerProduto(String codigoDeBarras, String cpf) {

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        Produto produto = validacaoService.validarProdutoPorCodigoDeBarras(codigoDeBarras);

        transacaoService.registrarTransacao(farmaceutico.getNome(), produto.getNome(), produto.getCodigoDeBarras(), 0, "REMOÇÃO DO PRODUTO.");

        produtoRepository.delete(produto);

    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        Produto produtoAtual = new Produto();
        produtoAtual = produtoRepository.findById(produto.getId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoAtual.setNome(produto.getNome());
        produtoAtual.setPreco(produto.getPreco());
        produtoAtual.setDescricao(produto.getDescricao());
        produtoAtual.setCodigoDeBarras(produto.getCodigoDeBarras());
        
        produtoRepository.save(produtoAtual);

        return produtoAtual;
    }


}