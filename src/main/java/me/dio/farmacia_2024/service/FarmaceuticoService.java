package me.dio.farmacia_2024.service;

import me.dio.farmacia_2024.domain.model.Farmaceutico;

public interface FarmaceuticoService {

    Farmaceutico cadastrarFarmaceutico(Farmaceutico farmaceutico);

    Farmaceutico encontrarFarmaceutico(String cpf);

    void deletarFarmaceutico(String cpf);
    
}
