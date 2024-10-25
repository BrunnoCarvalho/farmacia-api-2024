package me.dio.farmacia_2024.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.farmacia_2024.domain.model.Farmaceutico;
import me.dio.farmacia_2024.service.FarmaceuticoService;


@Tag(name = "01. Farmacêutico", description = "Operações para gerenciar farmacêuticos.")
@RestController
@RequestMapping("/farmaceutico")
public class FarmaceuticoController {

    @Autowired
    FarmaceuticoService farmaceuticoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarFarmaceutico(@RequestBody Farmaceutico farmaceutico) {
        try{
            farmaceuticoService.cadastrarFarmaceutico(farmaceutico);
            return ResponseEntity.ok("Farmacêutico cadastrado com sucesso");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro no cadastro.");
        }
    }

    @GetMapping
    public ResponseEntity<Farmaceutico> encontrarFarmaceutico(@Parameter(description = "CPF do farmacêutico a ser encontrado. Deve ser passado no cabeçalho da requisição.")
    @RequestHeader("cpf") String cpf){
        try{
            Farmaceutico farmaceutico = farmaceuticoService.encontrarFarmaceutico(cpf);
            return ResponseEntity.ok(farmaceutico);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarFarmaceutico(@Parameter(description = "CPF do farmacêutico a ser deletado. Deve ser passado no cabeçalho da requisição")
    @RequestHeader("cpf") String cpf){

        try{
            farmaceuticoService.deletarFarmaceutico(cpf);
            return ResponseEntity.ok("Cadastro do farmacêutico deletado com sucesso.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmacêutico não encontrado");
        }
    }
    
}