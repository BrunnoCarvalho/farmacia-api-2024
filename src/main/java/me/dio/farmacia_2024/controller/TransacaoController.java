package me.dio.farmacia_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.farmacia_2024.domain.model.Transacao;
import me.dio.farmacia_2024.service.TransacaoService;

@Tag(name = "05. Transações", description = "Operações para visualização das transações.")
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;
    
    @GetMapping("/listar-transacoes")
    public ResponseEntity<List<Transacao>> visualizarTransacoesDoFarmaceutico(@Parameter(description = "Nome do farmacêutico. Deve ser passado no cabeçalho da requisição.")
    @RequestHeader("nome") String nome){

        List<Transacao> transacoes = transacaoService.visualizarTransacoesDoFarmaceutico(nome);

        if (transacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(transacoes);
    }

    @GetMapping("/visualizar-transacoes")
    public ResponseEntity<List<Transacao>> visualizarTransacoes() {
        
        List<Transacao> transacoes = transacaoService.visualizarTransacoes();

        if (transacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(transacoes);
    }
    
}