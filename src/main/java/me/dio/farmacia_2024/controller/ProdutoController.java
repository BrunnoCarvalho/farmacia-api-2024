package me.dio.farmacia_2024.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.farmacia_2024.domain.model.Produto;
import me.dio.farmacia_2024.dto.ProdutoDto;
import me.dio.farmacia_2024.service.ProdutoService;

@Tag(name = "02. Produtos", description = "Operações para gerenciar produtos. As operações de cadastro e remoção só podem ser feitas por um farmacêutico já cadastrado no sistema.")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/cadastrar-produto")
    public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoDto produtoDto) {
        try{
            produtoService.cadastrarProduto(produtoDto.getProduto(), produtoDto.getCpf());
            return ResponseEntity.ok("Produto cadastrado com sucesso.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmacêutico não encontrado ou produto já cadastrado (mesmo código de barras).");
        }
    }
    
    @DeleteMapping("/remover-produto")
    public ResponseEntity<String> removerProduto(@RequestParam(value = "codigoDeBarras") String codigoDeBarras, @Parameter(description = "CPF do farmacêutico. Deve ser passado no cabeçalho da requisição")
    @RequestHeader("cpf") String cpf) {
        try{
            produtoService.removerProduto (codigoDeBarras, cpf);
            return ResponseEntity.ok("Produto removido com sucesso.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível remover o produto.");
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarProduto(@RequestBody ProdutoDto produtoDto) {
        try {
            produtoService.atualizarProduto(produtoDto.getProduto());
            return ResponseEntity.ok("Produto atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao atualizar produto.");
        }
    }

    @GetMapping("/listar-produtos")
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produtos = produtoService.listarProdutos();

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(produtos);
    }
    
}