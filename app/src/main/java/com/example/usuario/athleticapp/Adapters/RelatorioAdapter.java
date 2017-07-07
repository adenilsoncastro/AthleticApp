package com.example.usuario.athleticapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.athleticapp.Model.Checkout;
import com.example.usuario.athleticapp.Model.ClienteProduto;
import com.example.usuario.athleticapp.Model.Produto;
import com.example.usuario.athleticapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by oluis on 05/07/2017.
 */

public class RelatorioAdapter extends BaseAdapter {

    private final Activity activity;
    private List<Checkout> listCheckout;

    public RelatorioAdapter(Activity activity, List<Checkout> listCheckout){
        this.activity = activity;
        this.listCheckout = listCheckout;
    }

    public void add(Checkout checkout){
        listCheckout.add(checkout);
    }

    @Override
    public int getCount() {
        return listCheckout.size();
    }

    @Override
    public Object getItem(int position) {
        return listCheckout.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.list_relatorio, parent, false);
        Checkout checkout= listCheckout.get(position);

        TextView lblNome = (TextView) view.findViewById(R.id.txtNomeCliente);
        TextView lblPreco = (TextView) view.findViewById(R.id.txtPrecoTotal);
        TextView lblData = (TextView) view.findViewById(R.id.txtData) ;

        lblNome.setText(checkout.getNomeCliente());
        lblPreco.setText("R$ " + String.valueOf(checkout.getValor()));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        lblData.setText(formato.format(checkout.getData()));

        return view;
    }
}
