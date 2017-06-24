package com.example.usuario.athleticapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbWraper extends SQLiteOpenHelper {

    public static final String PRODUTOS = "Produtos";
    public static final String PRODUTO_ID = "_id";
    public static final String PRODUTO_NOME = "_nome";
    public static final String PRODUTO_PRECO = "_preco";
    public static final String PRODUTO_IMAGEM = "_imagem";

    private static final String DATABASE_NAME = "AthleticApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + PRODUTOS
            + "(" + PRODUTO_ID + " integer primary key autoincrement, "
            + PRODUTO_NOME + " text not null, "
            + PRODUTO_PRECO + " text not null, "
            + PRODUTO_IMAGEM + " integer not null)";

    public DbWraper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + PRODUTOS);
        onCreate(db);
    }
}
