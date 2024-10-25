package me.dio.farmacia_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.farmacia_2024.domain.model.Estoque;
import me.dio.farmacia_2024.dto.EstoqueDto;
import me.dio.farmacia_2024.service.EstoqueService;

@Tag(name = "03. Estoque", description = "Operações para gerenciar estoques. As operações de adição e remoção de um estoque só podem ser feitas por um farmacêutico já cadastrado no sistema.")
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarEstoque(@RequestBody EstoqueDto estoqueDto){
        try{
            estoqueService.adicionarEstoque(estoqueDto.getEstoque(), estoqueDto.getCpf());
            return ResponseEntity.ok("Estoque adicionado com sucesso.");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não encontrado.");
        }

    }

    @DeleteMapping("/remover-estoque")
    public ResponseEntity<String> removerEstoque(@RequestParam(value = "id") Long id,@Parameter(description = "CPF do farmacêutico.")
    @RequestHeader("cpf") String cpf ) {
        try {
            estoqueService.removerEstoque(id, cpf);
            return ResponseEntity.ok("Estoque removido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque ou CPF não encontrado.");
        }
    }

    @GetMapping("/visualizar-estoques")
    public ResponseEntity<List<Estoque>> listarEstoques(){
        List<Estoque> estoques = estoqueService.listarEstoques();

        if (estoques.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/visualizar-estoque")
    public ResponseEntity<Estoque> visualizarEstoque(@RequestParam(value = "id")Long id) {
        try {
            Estoque estoque = estoqueService.visualizarEstoque(id);
            return ResponseEntity.ok(estoque);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/visualizar-quantidade-estoque")
    public ResponseEntity<Integer> visualizarQuantidadeTotalDoEstoque(@RequestParam(value = "id") Long id) {
        try{
            Integer quantidade = estoqueService.visualizarQuantidadeTotalDoEstoque(id);
            return ResponseEntity.ok(quantidade);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
}