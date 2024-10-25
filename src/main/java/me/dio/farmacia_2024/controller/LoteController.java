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
import me.dio.farmacia_2024.domain.model.Lote;
import me.dio.farmacia_2024.dto.LoteDto;
import me.dio.farmacia_2024.service.LoteService;

@Tag(name = "04. Lote", description = "Operações para gerenciar lotes. É necessário ter um farmacêutico cadastrado para operar lotes. Para adicionar um lote é preciso ter um estoque cadastrado ao qual ele irá se referir. Um lote pode ser adicionado com um produto ou ser atualizado.")
@RestController
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    LoteService loteService;

    @PostMapping("/adicionar-lote-e-produto")
    public ResponseEntity<String> adicionarLoteComProduto(@RequestBody LoteDto loteDto) {
        try{
            loteService.adicionarLoteComProduto(
                loteDto.getEstoqueId(),
                loteDto.getNumeroLote(),
                loteDto.getQuantidade(),
                loteDto.getDataValidade(),
                loteDto.getNomeProduto(),
                loteDto.getCpf()
                );
            return ResponseEntity.ok("Lote e produto adicionados com sucesso");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmacêutico não encontrado ou dados passados incorretamente.");
        }
    }

    @PutMapping("/adicionar-produto")
    public ResponseEntity<String> adicionarQuantidadeProduto(@RequestBody LoteDto loteDto) {
        try{
            loteService.adicionarQuantidadeProduto(
                loteDto.getEstoqueId(),
                loteDto.getNumeroLote(),
                loteDto.getQuantidade(),
                loteDto.getDataValidade(),
                loteDto.getNomeProduto(),
                loteDto.getCpf()
                );
            return ResponseEntity.ok("Produto adicionado com sucesso.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lote ou estoque não encontrado para adição do produto.");
        }
    }

    @DeleteMapping("/remover-lote")
    public ResponseEntity<String> removerLote(@RequestParam(value = "estoqueId") Long estoqueId, @RequestParam(value = "numeroLote")  String numeroLote,
    @Parameter(description = "CPF do farmacêutico.") @RequestHeader("cpf") String cpf) {
        try{
            loteService.removerLote(estoqueId, numeroLote, cpf);
            return ResponseEntity.ok("Lote removido com sucesso.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lote ou estoque não encontrado para remoção");
        }
    }

    @PutMapping("/remover-quantidade-produto")
    public ResponseEntity<String> removerQuantidadeProduto(@RequestBody LoteDto loteDto){

        try{
            loteService.removerQuantidadeProduto(
                loteDto.getEstoqueId(),
                loteDto.getNumeroLote(),
                loteDto.getQuantidade(),
                loteDto.getDataValidade(),
                loteDto.getNomeProduto(),
                loteDto.getCpf()
                );
            return ResponseEntity.ok("Quantia removida do produto com sucesso.");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lote ou estoque não encontrado para remoção da quantia do produto.");
        }
    }
    
    @GetMapping("/verificar-quantidade-lote")
    public ResponseEntity<Integer> verificarQuantiaDeUmLote(@RequestParam(value = "estoqueId") Long estoqueId, @RequestParam(value = "numeroLote") String numeroLote){
        try{
            int quantidade = loteService.verificarQuantiaDeUmLote(estoqueId, numeroLote);
            return ResponseEntity.ok(quantidade);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("/verificar-lotes-de-um-estoque")
    public ResponseEntity<List<Lote>> verificarLotes(@RequestParam(value = "estoqueId") Long estoqueId) {

        List<Lote> lotes = loteService.verificarLotes(estoqueId);

        if (lotes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lotes);
    }
    
}