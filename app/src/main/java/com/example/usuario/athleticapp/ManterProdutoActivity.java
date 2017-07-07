package com.example.usuario.athleticapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Data.ProdutoOperations;
import com.example.usuario.athleticapp.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ManterProdutoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private EditText txtNomeProduto;
    private SeekBar skPreco;
    private TextView lblPreco;
    private Spinner ddlImagem;

    private ListView list;
    private List<Produto> listProduto;

    private ProdutoOperations produtoOperations;
    private ProdutoAdapter produtoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_produto);

        produtoOperations = new ProdutoOperations(this);
        produtoOperations.open();

        txtNomeProduto = (EditText) findViewById(R.id.txtNomeProduto);
        lblPreco = (TextView) findViewById(R.id.lblPreco);
        skPreco = (SeekBar) findViewById(R.id.skPreco);
        ddlImagem = (Spinner) findViewById(R.id.ddlImagens);
        skPreco.setOnSeekBarChangeListener(this);

        BuildListView();
        BuildSpinnerImagem();
    }

    private void BuildListView(){
        List values = produtoOperations.getAll();
        produtoAdapter = new ProdutoAdapter(this, values);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(produtoAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Produto produtoSelecionado = (Produto)produtoAdapter.getItem(position);
                ConfirmDelete(produtoSelecionado.getId());

            }
        });
    }

    public void ConfirmDelete(final long idProduto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManterProdutoActivity.this);
        builder.setMessage("Deseja excluir o produto?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        produtoOperations.deleteById(idProduto);
                        BuildListView();
                    }
                })
                .setNegativeButton("Não", null);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void btnAddProdutosClick(View v){

        if(txtNomeProduto.getText().toString().isEmpty()){
            Toast.makeText(ManterProdutoActivity.this, "Informe um nome", Toast.LENGTH_LONG).show();
            return;
        }

        if(skPreco.getProgress() == 0){
            Toast.makeText(ManterProdutoActivity.this, "Informe o preco", Toast.LENGTH_LONG).show();
            return;
        }

        Produto produto = new Produto();

        produto.setNome(txtNomeProduto.getText().toString());
        produto.setPreco(Integer.toString(skPreco.getProgress()));
        produto.setIcone(GetIconeFromView());

        Produto prodSalvo = produtoOperations.add(produto);
        ProdutoAdapter adapter = (ProdutoAdapter) list.getAdapter();
        adapter.add(prodSalvo);
    }

    public void btnVoltarClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        lblPreco.setText("R$ " + Integer.toString(progress));
    }

    private int GetIconeFromView(){
        String valor = ddlImagem.getSelectedItem().toString();

        if(valor == "ball") {
            return R.drawable.ball;
        }
        else if(valor == "bike"){
            return R.drawable.bike;
        }
        else
            return 0;
    }

    private void BuildSpinnerImagem()
    {
        List<String> listImagens = new ArrayList<String>();
        listImagens.add("ball");
        listImagens.add("bike");
        listImagens.add("trophy");
        ArrayAdapter imgAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listImagens);
        ddlImagem.setAdapter(imgAdapter);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
