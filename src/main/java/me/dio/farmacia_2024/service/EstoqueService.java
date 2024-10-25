package me.dio.farmacia_2024.service;
import java.util.List;

import me.dio.farmacia_2024.domain.model.Estoque;

public interface EstoqueService {

    void adicionarEstoque(Estoque estoque, String cpf);

    void removerEstoque(Long id, String cpf);

    List<Estoque> listarEstoques();

    Estoque visualizarEstoque(Long id);

    int visualizarQuantidadeTotalDoEstoque(Long id);
    
}
