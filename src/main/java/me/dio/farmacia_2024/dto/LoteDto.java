package me.dio.farmacia_2024.dto;
import java.time.LocalDate;

public class LoteDto extends FarmaceuticoDto {
    
    private Long estoqueId;
    private String numeroLote;
    private int quantidade;
    private LocalDate dataValidade;
    private String nomeProduto;

    public Long getEstoqueId() {
        return estoqueId;
    }
    public void setEstoqueId(Long estoqueId) {
        this.estoqueId = estoqueId;
    }
    public String getNumeroLote() {
        return numeroLote;
    }
    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
}
