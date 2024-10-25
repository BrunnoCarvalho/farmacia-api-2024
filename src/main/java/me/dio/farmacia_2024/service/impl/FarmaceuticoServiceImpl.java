package me.dio.farmacia_2024.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.domain.repository.FarmaceuticoRepository;
import me.dio.farmacia_2024.domain.repository.TransacaoRepository;
import me.dio.farmacia_2024.service.FarmaceuticoService;
import me.dio.farmacia_2024.service.ValidacaoService;

@Service
public class FarmaceuticoServiceImpl implements FarmaceuticoService {

    @Autowired
    FarmaceuticoRepository farmaceuticoRepository;

    @Autowired
    ValidacaoService validacaoService;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Override
    public Farmaceutico cadastrarFarmaceutico(Farmaceutico farmaceutico) {
        return farmaceuticoRepository.save(farmaceutico);
    }

    @Override
    public Farmaceutico encontrarFarmaceutico(String cpf) {

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);
        
        return farmaceutico;
    }

    @Override
    public void deletarFarmaceutico(String cpf) {

        Farmaceutico farmaceutico = validacaoService.validarFarmaceutico(cpf);

        farmaceuticoRepository.delete(farmaceutico);
    }

}