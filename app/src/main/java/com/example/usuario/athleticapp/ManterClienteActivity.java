package com.example.usuario.athleticapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ClienteAdapter;
import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Data.ClienteOperations;
import com.example.usuario.athleticapp.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ManterClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;

    private ListView list;
    private List<Cliente> clienteList;

    private ClienteOperations clienteOperations;
    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_cliente);

        clienteOperations = new ClienteOperations(this);
        clienteOperations.open();

        nome = (EditText)findViewById(R.id.editTextNome_Cliente);
        cpf = (EditText)findViewById(R.id.editTextCPF_Cliente);
        telefone = (EditText)findViewById(R.id.editTextTelefone_Cliente);

        clienteList = new ArrayList<Cliente>();
        clienteList.add(new Cliente(1,"Adenilson","4648546","64646"));
        clienteList.add(new Cliente(2,"Luis","4648546","64646"));

        final ClienteAdapter clienteAdapter = new ClienteAdapter(this, clienteList);
        list = (ListView)findViewById(R.id.listClientes);
        list.setAdapter(clienteAdapter);

    }

    public void addCliente(View view)
    {
        Cliente cliente = clienteOperations.addCliente(nome.getText().toString(),cpf.getText().toString(), Integer.parseInt(telefone.getText().toString()));
        ClienteAdapter adapter = (ClienteAdapter)list.getAdapter();
        adapter.add(cliente);
    }

    public void btnVoltarClienteClick(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
