package com.example.usuario.athleticapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Data.ProdutoOperations;
import com.example.usuario.athleticapp.Model.ClienteProduto;
import com.example.usuario.athleticapp.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private List<Produto> listProduto;
    private ProdutoOperations produtoOperations;

    private Spinner ddlProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ddlProduto = (Spinner) findViewById(R.id.ddlProdutos);

        produtoOperations = new ProdutoOperations(this);
        produtoOperations.open();
        final List<Produto> listProd = produtoOperations.getAll();

        BuildListView(listProd);
        BuildSpinnerProduto(listProd);
    }

    private void BuildSpinnerProduto(List<Produto> listProduto)
    {
        List<String> ls = new ArrayList<String>();

        for(Produto p : listProduto){
            ls.add(p.getNome() + " R$" + p.getPreco());
        }
        ls.add(0, "Selecione");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ls);
        ddlProduto.setAdapter(adapter);
    }

    private void BuildListView(final List<Produto> listProduto){
        final ProdutoAdapter produtoAdapter = new ProdutoAdapter(this, listProduto);
        list = (ListView)findViewById(R.id.listMain);
        list.setAdapter(produtoAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoSelecionado = listProduto.get(position);
                Toast.makeText(MainActivity.this, "Produto selecionado " + produtoSelecionado.getNome(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btnProdutosClick(View v){
        Intent i = new Intent(this, ManterProdutoActivity.class);
        startActivity(i);
    }

    public void btnClientesClick(View view){
        Intent i = new Intent(this, ManterClienteActivity.class);
        startActivity(i);
    }
}
