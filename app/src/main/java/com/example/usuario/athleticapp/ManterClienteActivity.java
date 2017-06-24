package com.example.usuario.athleticapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ClienteAdapter;
import com.example.usuario.athleticapp.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ManterClienteActivity extends AppCompatActivity {

    private ListView list;
    private List<Cliente> clienteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_cliente);

        clienteList = new ArrayList<Cliente>();
        clienteList.add(new Cliente("Adenilson","4648546","64646"));
        clienteList.add(new Cliente("Luis","4648546","64646"));

        final ClienteAdapter clienteAdapter = new ClienteAdapter(this, clienteList);
        list = (ListView)findViewById(R.id.listClientes);
        list.setAdapter(clienteAdapter);

    }



    public void btnVoltarClienteClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
