package me.dio.farmacia_2024.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.farmacia_2024.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Produto findByCodigoDeBarras(String codigoDeBarras);
    
    Produto findByNome(String nome);
}
