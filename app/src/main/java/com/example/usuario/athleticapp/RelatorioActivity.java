package com.example.usuario.athleticapp;

import android.content.Intent;
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

        if(dataInicial.length() < 10 || dataFinal.length() < 10 ){
            Toast.makeText(RelatorioActivity.this, "Data informada inválida", Toast.LENGTH_LONG).show();
            return;
        }

        if(dataInicial.length() > 10 || dataFinal.length() > 10 ){
            Toast.makeText(RelatorioActivity.this, "Data informada inválida", Toast.LENGTH_LONG).show();
            return;
        }

        int countInicial = dataInicial.length() - dataInicial.replace("/", "").length();
        int countFinal = dataFinal.length() - dataInicial.replace("/", "").length();

        if(countInicial < 2 || countFinal < 2){
            Toast.makeText(RelatorioActivity.this, "Data em formato inválido", Toast.LENGTH_LONG).show();
            return;
        }

        if(dataInicial != null) {
            dataInicial = dataInicial.replace("/", "-");

            String[] initialDate = dataInicial.split("-");
            if(Integer.parseInt(initialDate[0]) < 1  || Integer.parseInt(initialDate[0]) > 31 ){
                Toast.makeText(RelatorioActivity.this, "Dia inicial inválido", Toast.LENGTH_LONG).show();
            }

            if(Integer.parseInt(initialDate[1]) < 1 || Integer.parseInt(initialDate[0]) > 12 ){
                Toast.makeText(RelatorioActivity.this, "Mês inicial inválido", Toast.LENGTH_LONG).show();
            }

            if(Integer.parseInt(initialDate[2]) < 1950 || Integer.parseInt(initialDate[0]) > 9999 ){
                Toast.makeText(RelatorioActivity.this, "Ano inicial inválido", Toast.LENGTH_LONG).show();
            }

        }
        if(dataFinal != null){
            dataFinal = dataFinal.replace("/","-");

            String[] finalDate = dataInicial.split("-");
            if(Integer.parseInt(finalDate[0]) < 1  || Integer.parseInt(finalDate[0]) > 31 ){
                Toast.makeText(RelatorioActivity.this, "Dia final inválido", Toast.LENGTH_LONG).show();
            }

            if(Integer.parseInt(finalDate[1]) < 1 || Integer.parseInt(finalDate[0]) > 12 ){
                Toast.makeText(RelatorioActivity.this, "Mês final inválido", Toast.LENGTH_LONG).show();
            }

            if(Integer.parseInt(finalDate[2]) < 1950 || Integer.parseInt(finalDate[0]) > 9999 ){
                Toast.makeText(RelatorioActivity.this, "Ano final inválido", Toast.LENGTH_LONG).show();
            }
        }

        List<Checkout> listFiltrada = checkoutOperations.Filtrar(dataInicial, dataFinal);

        BuildListView(listFiltrada);
    }

    public void voltar(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
