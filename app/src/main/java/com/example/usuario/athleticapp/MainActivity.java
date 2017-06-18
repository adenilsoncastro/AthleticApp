package com.example.usuario.athleticapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private List<Produto> listProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProduto = new ArrayList<Produto>();

        listProduto.add(new Produto("bike", "100", R.drawable.bike));
        listProduto.add(new Produto("ball", "9", R.drawable.ball));

        final ProdutoAdapter produtoAdapter = new ProdutoAdapter(this, listProduto);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(produtoAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produtoSelecionado = listProduto.get(position);
                Toast.makeText(MainActivity.this, "Produto selecionado " + produtoSelecionado.getNome(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
