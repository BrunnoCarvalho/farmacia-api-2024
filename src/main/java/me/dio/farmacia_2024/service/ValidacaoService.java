package me.dio.farmacia_2024.service;

import me.dio.farmacia_2024.domain.model.Estoque;
import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.domain.model.Lote;
import me.dio.farmacia_2024.domain.model.Produto;

public interface ValidacaoService {

    Estoque validarEstoque(Long estoqueId);

    Produto validarProduto(String nomeProduto);

    Farmaceutico validarFarmaceutico(String cpf);
    
    Lote validarLote(String numeroLote, Long estoqueId);

    Produto validarProdutoPorCodigoDeBarras(String codigoDeBarras);

}