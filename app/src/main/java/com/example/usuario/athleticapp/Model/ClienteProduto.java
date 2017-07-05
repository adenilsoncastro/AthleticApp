package com.example.usuario.athleticapp.Model;

import java.util.List;

public class ClienteProduto {

    private Cliente cliente;
    private List<Produto> listProduto;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getListProduto() {
        return listProduto;
    }

    public void setListProduto(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }
}
