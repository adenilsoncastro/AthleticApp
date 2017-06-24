package com.example.usuario.athleticapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.athleticapp.Model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoOperations {

    private SQLiteDatabase database;
    private DbWraper dbHelper;
    private String[] PRODUTO_TABLE_COLUMNS = {dbHelper.PRODUTO_ID,
            dbHelper.PRODUTO_NOME,
            dbHelper.PRODUTO_PRECO,
            dbHelper.PRODUTO_IMAGEM};

    public ProdutoOperations(Context context){
        dbHelper = new DbWraper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Produto add(Produto produto){
        ContentValues values = new ContentValues();

        values.put(DbWraper.PRODUTO_NOME, produto.getNome());
        values.put(DbWraper.PRODUTO_PRECO, produto.getPreco());
        values.put(DbWraper.PRODUTO_IMAGEM, produto.getIcone());

        long id = database.insert(DbWraper.PRODUTOS, null, values);

        Cursor cursor = database.query(DbWraper.PRODUTOS,
                PRODUTO_TABLE_COLUMNS, DbWraper.PRODUTO_ID + " = "
                        + id, null, null, null, null );

        cursor.moveToFirst();

        Produto produtoNew = parseProduto(cursor);
        cursor.close();

        return produtoNew;
    }

    public void delete(Produto produto){
        long id = produto.getId();
        database.delete(DbWraper.PRODUTOS, DbWraper.PRODUTO_ID+ " = " + id, null);
    }

    public List getAll() {
        List listProdutos = new ArrayList<Produto>();
        Cursor cursor = database.query(DbWraper.PRODUTOS,
                PRODUTO_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Produto produto = new Produto();
            listProdutos.add(produto);
            cursor.moveToNext();
        }

        cursor.close();
        return listProdutos;
    }

    private Produto parseProduto(Cursor cursor){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(0));
        produto.setNome(cursor.getString(1));
        produto.setPreco(cursor.getString(2));
        produto.setIcone(cursor.getInt(3));
        return produto;
    }
}
