package me.dio.farmacia_2024.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.farmacia_2024.domain.model.Farmaceutico;

public interface FarmaceuticoRepository extends JpaRepository<Farmaceutico, Long>{

    Farmaceutico findByCpf(String cpf);

}
