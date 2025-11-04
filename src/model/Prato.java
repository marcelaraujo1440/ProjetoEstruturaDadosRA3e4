package model;

public class Prato {
    private String nome;
    private double preco;
    private int tempoPreparo;
    public Prato(String nome, double preco, int tempoPreparo) {
        this.nome = nome;
        this.preco = preco;
        this.tempoPreparo = tempoPreparo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getTempoPreparo() {
        return tempoPreparo;
    }
    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
    @Override
    public String toString(){
        return "Prato: " + getNome() + "| Preço: " + getPreco() + "| Tempo de preparo: " + getTempoPreparo();
    }
}
