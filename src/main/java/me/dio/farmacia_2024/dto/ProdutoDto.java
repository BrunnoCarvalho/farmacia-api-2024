package me.dio.farmacia_2024.dto;

import me.dio.farmacia_2024.domain.model.Produto;

public class ProdutoDto extends FarmaceuticoDto {

    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
