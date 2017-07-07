package com.example.usuario.athleticapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.athleticapp.Adapters.ClienteAdapter;
import com.example.usuario.athleticapp.Adapters.ProdutoAdapter;
import com.example.usuario.athleticapp.Data.ClienteOperations;
import com.example.usuario.athleticapp.Model.Cliente;
import com.example.usuario.athleticapp.Model.ClienteProduto;

import java.util.ArrayList;
import java.util.List;

public class ManterClienteActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private Button btnVoltar;

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
        btnVoltar = (Button)findViewById(R.id.buttonVoltarClientes);

        clienteList = new ArrayList<Cliente>();

        final ClienteAdapter clienteAdapter = new ClienteAdapter(this, clienteList);

        BuildListView();
    }

    private void BuildListView(){
        List values = clienteOperations.getAll();
        clienteAdapter = new ClienteAdapter(this, values);
        list = (ListView)findViewById(R.id.listClientes);
        list.setAdapter(clienteAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cliente clienteSelecionado = (Cliente)clienteAdapter.getItem(position);
                ConfirmDelete(clienteSelecionado.getId());

            }
        });
    }

    public void ConfirmDelete(final long idCliente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManterClienteActivity.this);
        builder.setMessage("Deseja excluir o cliente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        clienteOperations.deleteById(idCliente);
                        BuildListView();
                    }
                })
                .setNegativeButton("Não", null);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void addCliente(View view)
    {
        if(nome.getText().toString().length() != 0)
        {
            if(cpf.getText().toString().length() != 0)
            {
                if(telefone.getText().toString().length() != 0)
                {
                    Cliente cliente = clienteOperations.addCliente(nome.getText().toString(),cpf.getText().toString(), Integer.parseInt(telefone.getText().toString()));
                    ClienteAdapter adapter = (ClienteAdapter)list.getAdapter();
                    adapter.add(cliente);
                }
                else
                {
                    Toast.makeText(ManterClienteActivity.this, "Informe um telefone", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(ManterClienteActivity.this, "Informe um CPF", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(ManterClienteActivity.this, "Informe um nome", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void voltar(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
