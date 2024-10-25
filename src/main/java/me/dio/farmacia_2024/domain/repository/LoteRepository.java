package me.dio.farmacia_2024.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.farmacia_2024.domain.model.Lote;

public interface LoteRepository extends JpaRepository<Lote,Long> {

    Lote findByNumeroLote(String numeroLote);

    Optional<Lote> findByNumeroLoteAndEstoqueId(String numeroLote, Long estoqueId);
    
    List<Lote> findByEstoqueId(Long estoqueId);
}
