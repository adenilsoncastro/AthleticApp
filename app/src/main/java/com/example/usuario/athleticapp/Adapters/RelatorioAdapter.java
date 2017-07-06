package com.example.usuario.athleticapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.athleticapp.Model.ClienteProduto;
import com.example.usuario.athleticapp.Model.Produto;
import com.example.usuario.athleticapp.R;

import java.util.List;

/**
 * Created by oluis on 05/07/2017.
 */

public class RelatorioAdapter extends BaseAdapter {

    private final Activity activity;
    private List<ClienteProduto> listClienteProduto;

    public RelatorioAdapter(Activity activity, List<ClienteProduto> listProduto){
        this.activity = activity;
        this.listClienteProduto = listProduto;
    }

    public void add(ClienteProduto clienteProduto){
        listClienteProduto.add(clienteProduto);
    }

    @Override
    public int getCount() {
        return listClienteProduto.size();
    }

    @Override
    public Object getItem(int position) {
        return listClienteProduto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.list_produto, parent, false);
        ClienteProduto clienteProduto = listClienteProduto.get(position);

        TextView lblNome = (TextView) view.findViewById(R.id.lblNome);
        TextView lblPreco = (TextView) view.findViewById(R.id.lblPreco);

        lblNome.setText(clienteProduto.getCliente().getNome());

        return view;
    }
}
