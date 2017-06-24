package com.example.usuario.athleticapp.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.athleticapp.Model.Produto;
import com.example.usuario.athleticapp.R;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private final Activity activity;
    private List<Produto> listProduto;

    public ProdutoAdapter(Activity activity, List<Produto> listProduto){
        this.activity = activity;
        this.listProduto = listProduto;
    }

    public void add(Produto produto){
        listProduto.add(produto);
    }

    @Override
    public int getCount() {
        return listProduto.size();
    }

    @Override
    public Object getItem(int position) {
        return listProduto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.list_produto, parent, false);
        Produto produto = listProduto.get(position);

        TextView lblNome = (TextView) view.findViewById(R.id.lblNome);
        TextView lblPreco = (TextView) view.findViewById(R.id.lblPreco);
        ImageView imgView = (ImageView) view.findViewById(R.id.img);

        lblNome.setText(produto.getNome());
        lblPreco.setText(produto.getPreco());
        imgView.setImageResource(produto.getIcone());

        return view;
    }
}
