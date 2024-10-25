package me.dio.farmacia_2024.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.dio.farmacia_2024.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByNome(String nome);

}