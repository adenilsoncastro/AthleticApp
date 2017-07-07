package com.example.usuario.athleticapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Adapters.RelatorioAdapter;
import com.example.usuario.athleticapp.Data.CheckoutOperations;
import com.example.usuario.athleticapp.Model.Checkout;
import com.example.usuario.athleticapp.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    private ListView list;
    private CheckoutOperations checkoutOperations;
    private List<Checkout> listCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        listCheckout = new ArrayList<Checkout>();

        checkoutOperations = new CheckoutOperations(this);
        checkoutOperations.open();

        listCheckout = checkoutOperations.getAll();

        BuildListView(listCheckout);
    }

    private void BuildListView(final List<Checkout> listChk){
        final RelatorioAdapter relatorioAdapter = new RelatorioAdapter(this, listChk);
        list = (ListView)findViewById(R.id.listRelatorio);
        list.setAdapter(relatorioAdapter);
    }
}
