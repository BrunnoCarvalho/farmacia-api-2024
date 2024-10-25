package me.dio.farmacia_2024.dto;

import me.dio.farmacia_2024.domain.model.Estoque;

public class EstoqueDto extends FarmaceuticoDto {

    private Estoque estoque;

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

}