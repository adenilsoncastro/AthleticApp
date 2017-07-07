package com.example.usuario.athleticapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Data.CheckoutOperations;
import com.example.usuario.athleticapp.Data.ClienteOperations;
import com.example.usuario.athleticapp.Data.ProdutoOperations;
import com.example.usuario.athleticapp.Model.Checkout;
import com.example.usuario.athleticapp.Model.Cliente;
import com.example.usuario.athleticapp.Model.ClienteProduto;
import com.example.usuario.athleticapp.Model.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private List<Produto> listCarrinho;
    private List<Produto> listProduto;
    private List<Cliente> listCliente;
    private ProdutoOperations produtoOperations;
    private ClienteOperations clienteOperations;
    private CheckoutOperations checkoutOperations;

    private Spinner ddlProduto;
    private Spinner ddlCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ddlProduto = (Spinner) findViewById(R.id.ddlProdutos);
        ddlCliente = (Spinner) findViewById(R.id.ddlClientes);

        produtoOperations = new ProdutoOperations(this);
        clienteOperations = new ClienteOperations(this);
        checkoutOperations = new CheckoutOperations(this);

        produtoOperations.open();
        clienteOperations.open();
        checkoutOperations.open();

        listProduto = produtoOperations.getAll();
        listCliente = clienteOperations.getAll();
        listCarrinho = new ArrayList<Produto>();

        BuildListView(listCarrinho);
        BuildSpinnerProduto(listProduto);
        BuildSpinnerCliente(listCliente);

        ddlCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                listCarrinho.clear();
                BuildListView(listCarrinho);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void BuildSpinnerCliente(List<Cliente> listCliente)
    {
        List<String> ls = new ArrayList<String>();

        for(Cliente c : listCliente){
            ls.add(c.getNome());
        }
        ls.add(0, "Selecione");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ls);
        ddlCliente.setAdapter(adapter);
    }

    private void BuildSpinnerProduto(List<Produto> listProduto)
    {
        List<String> ls = new ArrayList<String>();

        for(Produto p : listProduto){
            //ls.add(p.getNome() + " R$" + p.getPreco());
            ls.add(p.getNome());
        }
        ls.add(0, "Selecione");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ls);
        ddlProduto.setAdapter(adapter);
    }

    private void BuildListView(final List<Produto> listCar){
        final ProdutoAdapter produtoAdapter = new ProdutoAdapter(this, listCar);
        list = (ListView)findViewById(R.id.listMain);
        list.setAdapter(produtoAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listCarrinho.remove(position);
                BuildListView(listCarrinho);
            }
        });
    }

    public void btnInserirClick(View v){
        String produtoNome = ddlProduto.getSelectedItem().toString();
        String clienteNome = ddlCliente.getSelectedItem().toString();
        Produto produto = new Produto();

        if(clienteNome == "Selecione"){
            Toast.makeText(MainActivity.this, "Selecione um cliente!", Toast.LENGTH_LONG).show();
            return;
        }

        for(Produto prod : listProduto){
            if(prod.getNome().contains(produtoNome)){
                produto = prod;
            }
        }

        listCarrinho.add(produto);
        BuildListView(listCarrinho);
    }

    public void btnCheckoutClick(View v){
        if(ddlCliente.getSelectedItem().toString() == "Selecione"){
            Toast.makeText(MainActivity.this, "Selecione um cliente!", Toast.LENGTH_LONG).show();
            return;
        }

        if(listCarrinho.size() == 0){
            Toast.makeText(MainActivity.this, "Insira um produto no carrinho!", Toast.LENGTH_LONG).show();
            return;
        }

        ConfirmCheckout();
    }

    public void btnProdutosClick(View v){
        Intent i = new Intent(this, ManterProdutoActivity.class);
        startActivity(i);
        finish();
    }

    public void btnClientesClick(View view){
        Intent i = new Intent(this, ManterClienteActivity.class);
        startActivity(i);
    }

    public void btnRelatorioClick(View v){
        Intent i = new Intent(this, RelatorioActivity.class);
        startActivity(i);
    }

    public void ConfirmCheckout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Deseja finalizar a compra?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Checkout();
                    }
                })
                .setNegativeButton("Não", null);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Checkout(){
        Checkout checkout = new Checkout();

        double valorTotal = 0;
        for(Produto p : listCarrinho){
            valorTotal += Double.parseDouble(p.getPreco());
        }

        checkout.setNomeCliente(ddlCliente.getSelectedItem().toString());
        checkout.setValor(valorTotal);
        checkout.setData(new Date());

        Checkout chkSalvo = checkoutOperations.addCheckout(checkout);

        Toast.makeText(MainActivity.this, "Compra realizada com sucesso!", Toast.LENGTH_LONG).show();

        ResetActivity();
    }

    public void ResetActivity(){
        listCliente = new ArrayList<Cliente>();
        listCarrinho = new ArrayList<Produto>();
        BuildSpinnerCliente(listCliente);
        BuildSpinnerProduto(listProduto);
        BuildListView(listCarrinho);
    }
}
