package me.dio.farmacia_2024.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Estoque;
import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.domain.model.Lote;
import me.dio.farmacia_2024.domain.model.Produto;
import me.dio.farmacia_2024.domain.repository.EstoqueRepository;
import me.dio.farmacia_2024.domain.repository.FarmaceuticoRepository;
import me.dio.farmacia_2024.domain.repository.LoteRepository;
import me.dio.farmacia_2024.domain.repository.ProdutoRepository;
import me.dio.farmacia_2024.service.ValidacaoService;

@Service
public class ValidacaoServiceImpl implements ValidacaoService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FarmaceuticoRepository farmaceuticoRepository;

    @Autowired
    private LoteRepository loteRepository;

    public Estoque validarEstoque(Long estoqueId) {

        return estoqueRepository.findById(estoqueId)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com o ID: " + estoqueId));

    }
    
    public Produto validarProduto(String nomeProduto) {

        Produto produto = produtoRepository.findByNome(nomeProduto);

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;

    }

    public Produto validarProdutoPorCodigoDeBarras(String codigoDeBarras) {

        Produto produto = produtoRepository.findByCodigoDeBarras(codigoDeBarras);

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;

    }

    public Farmaceutico validarFarmaceutico(String cpf) {

        Farmaceutico farmaceutico = farmaceuticoRepository.findByCpf(cpf);

        if (farmaceutico == null) {
            throw new IllegalArgumentException("Farmacêutico não encontrado");
        }
        return farmaceutico;

    }

    public Lote validarLote(String numeroLote, Long estoqueId) {

        Lote lote = loteRepository.findByNumeroLote(numeroLote);

        if (lote == null || !lote.getEstoque().getId().equals(estoqueId)) {
            throw new IllegalArgumentException("Lote não encontrado ou não pertence ao estoque informado");
        }
        return lote;

    }
}
