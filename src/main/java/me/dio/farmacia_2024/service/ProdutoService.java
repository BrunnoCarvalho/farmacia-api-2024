package me.dio.farmacia_2024.service;

import java.util.List;

import me.dio.farmacia_2024.domain.model.Produto;

public interface ProdutoService {

    Produto cadastrarProduto(Produto produto, String cpf);

    void removerProduto(String codigoDeBarras, String cpf);

    Produto atualizarProduto(Produto produto);

    List<Produto> listarProdutos();

}