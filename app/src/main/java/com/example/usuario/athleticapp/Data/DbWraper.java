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

    public static final String CLIENTES = "Clientes";
    public static final String CLIENTE_ID = "_id";
    public static final String CLIENTE_NOME = "_nome";
    public static final String CLIENTE_CPF = "_cpf";
    public static final String CLIENTE_TELEFONE = "_telefone";

    public static final String CHECKOUTS = "Checkouts";
    public static final String CHECKOUT_ID = "_id";
    public static final String CHECKOUT_NOME = "_nome";
    public static final String CHECKOUT_PRECO = "_preco";
    public static final String CHECKOUT_DATA = "_data";

    private static final String DATABASE_NAME = "AthleticApp.db";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE = "create table " + PRODUTOS
            + "(" + PRODUTO_ID + " integer primary key autoincrement, "
            + PRODUTO_NOME + " text not null, "
            + PRODUTO_PRECO + " text not null, "
            + PRODUTO_IMAGEM + " integer not null)";

    private static final String DATABASE_CREATE_CLIENTES = "create table " + CLIENTES
            + "(" + CLIENTE_ID + " integer primary key autoincrement, "
            + CLIENTE_NOME + " text not null, "
            + CLIENTE_CPF + " text not null, "
            + CLIENTE_TELEFONE + " text not null)";

    private static final String DATABASE_CREATE_CHECKOUT = "create table " + CHECKOUTS
            + "(" + CHECKOUT_ID + " integer primary key autoincrement, "
            + CHECKOUT_NOME + " text not null, "
            + CHECKOUT_PRECO + " real not null,"
            + CHECKOUT_DATA + " date not null)";

    public DbWraper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_CLIENTES);
        db.execSQL(DATABASE_CREATE_CHECKOUT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + PRODUTOS);
        db.execSQL("drop table if exists " + CLIENTES);
        db.execSQL("drop table if exists " + CHECKOUTS);
        onCreate(db);
    }
}
