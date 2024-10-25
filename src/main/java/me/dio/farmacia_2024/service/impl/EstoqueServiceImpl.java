package me.dio.farmacia_2024.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Estoque;
import me.dio.farmacia_2024.domain.repository.EstoqueRepository;
import me.dio.farmacia_2024.service.EstoqueService;
import me.dio.farmacia_2024.service.ValidacaoService;

@Service
public class EstoqueServiceImpl implements EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    ValidacaoService validacaoService;

    @Override
    public void adicionarEstoque(Estoque estoque, String cpf){

        validacaoService.validarFarmaceutico(cpf);
        estoque.setDataEntrada(LocalDate.now());
        estoqueRepository.save(estoque);

    }

    @Override
    public void removerEstoque(Long id, String cpf){

        validacaoService.validarFarmaceutico(cpf);

        Estoque estoque = new Estoque();
        estoque = estoqueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com o ID: " + id));
        estoqueRepository.delete(estoque);
    }

    @Override
    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }

    @Override
    public Estoque visualizarEstoque(Long id){
        return estoqueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com o ID: " + id));
    }

    @Override
    public int visualizarQuantidadeTotalDoEstoque(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com o ID: " + id));
    
        return estoque.calcularQuantidadeTotal();
    }

}
