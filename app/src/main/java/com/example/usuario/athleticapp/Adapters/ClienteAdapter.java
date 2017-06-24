package com.example.usuario.athleticapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.usuario.athleticapp.Model.Cliente;
import com.example.usuario.athleticapp.R;

import java.util.List;

/**
 * Created by Usuario on 23/06/2017.
 */

public class ClienteAdapter extends BaseAdapter{

    private List<Cliente> clienteList;
    private final Activity activity;

    public ClienteAdapter(Activity activity, List<Cliente> clienteList){
        this.activity = activity;
        this.clienteList = clienteList;
    }

    @Override
    public int getCount() {
        return clienteList.size();
    }

    @Override
    public Object getItem(int position) {
        return clienteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.list_clientes, parent, false);
        Cliente cliente = clienteList.get(position);

        TextView txtNome = (TextView) view.findViewById(R.id.textViewNome_Cliente);
        TextView txtCPF = (TextView) view.findViewById(R.id.textViewCPF_Cliente);
        TextView txtTelefone = (TextView) view.findViewById(R.id.textViewTelefone_Cliente);

        txtNome.setText(cliente.getNome());
        txtCPF.setText(cliente.getCpf());
        txtTelefone.setText(cliente.getTelefone());

        return view;
    }
}
