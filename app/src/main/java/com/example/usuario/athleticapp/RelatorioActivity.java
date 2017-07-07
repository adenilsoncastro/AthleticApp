package com.example.usuario.athleticapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Adapters.RelatorioAdapter;
import com.example.usuario.athleticapp.Data.CheckoutOperations;
import com.example.usuario.athleticapp.Model.Checkout;
import com.example.usuario.athleticapp.Model.Produto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    private ListView list;
    private CheckoutOperations checkoutOperations;
    private List<Checkout> listCheckout;
    private EditText txtInicio;
    private EditText txtFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        txtInicio = (EditText) findViewById(R.id.dataInicial);
        txtFim = (EditText) findViewById(R.id.dataFinal);

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

    public void btnFiltrarClick(View v){
        String dataInicial = txtInicio.getText().toString();
        String dataFinal = txtFim.getText().toString();

        if(dataInicial.isEmpty() || dataFinal.isEmpty()){
            Toast.makeText(RelatorioActivity.this, "Informe todos os campos do filtro", Toast.LENGTH_LONG).show();
            return;
        }

        if(dataInicial != null)
            dataInicial = dataInicial.replace("/","-");

        if(dataFinal != null){
            dataFinal = dataFinal.replace("/","-");
        }

        List<Checkout> listFiltrada = checkoutOperations.Filtrar(dataInicial, dataFinal);

        BuildListView(listFiltrada);
    }
}
