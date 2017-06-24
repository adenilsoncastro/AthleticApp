package com.example.usuario.athleticapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.athleticapp.Produto;

import java.util.List;

public class ProdutoSpinnerAdapter extends ArrayAdapter<Produto> {
    private Context context;
    // Your custom values for the spinner (User)
    private List<Produto> listProduto;

    public ProdutoSpinnerAdapter(Context context, int textViewResourceId, List<Produto> listProduto) {
        super(context, textViewResourceId, listProduto);
        this.context = context;
        this.listProduto = listProduto;
    }

    public int getCount(){
        return listProduto.size();
    }

    public Produto getItem(int position){
        return listProduto.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(listProduto.get(position).getNome());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(listProduto.get(position).getNome());

        return label;
    }
}
