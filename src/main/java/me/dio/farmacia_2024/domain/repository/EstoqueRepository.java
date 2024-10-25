package me.dio.farmacia_2024.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.farmacia_2024.domain.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    
}
