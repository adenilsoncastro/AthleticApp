package com.example.usuario.athleticapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ManterProdutoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private SeekBar skPreco;
    private TextView lblPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_produto);

        lblPreco = (TextView) findViewById(R.id.lblPreco);
        skPreco = (SeekBar) findViewById(R.id.skPreco);

        skPreco.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        lblPreco.setText(Integer.toString(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
