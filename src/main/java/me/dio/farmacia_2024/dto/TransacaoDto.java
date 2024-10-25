package me.dio.farmacia_2024.dto;

public class TransacaoDto {
    
    private String nomeFarmaceutico;
    private String nomeProduto;
    private String codigoDeBarras;
    private int quantidade;
    private String tipoTransacao;
    

    public String getNomeFarmaceutico() {
        return nomeFarmaceutico;
    }

    public void setNomeFarmaceutico(String nomeFarmaceutico) {
        this.nomeFarmaceutico = nomeFarmaceutico;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
    
}
