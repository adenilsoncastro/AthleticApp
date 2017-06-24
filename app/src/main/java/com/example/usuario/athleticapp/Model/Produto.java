package com.example.usuario.athleticapp.Model;

public class Produto {

    private String nome;
    private String preco;
    private int icone;

    public Produto(String Nome, String Preco, int Icone){
        this.nome = Nome;
        this.preco = Preco;
        this.icone = Icone;
    }

    public Produto(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }
}
