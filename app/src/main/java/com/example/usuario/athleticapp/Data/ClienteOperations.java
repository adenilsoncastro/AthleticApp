package com.example.usuario.athleticapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.athleticapp.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 05/07/2017.
 */

public class ClienteOperations {
    private DbWraper dbHelper;
    private String[] CLIENT_TABLE_COLUMNS = {dbHelper.CLIENTE_ID, dbHelper.CLIENTE_NOME, dbHelper.CLIENTE_CPF, dbHelper.CLIENTE_TELEFONE};
    private SQLiteDatabase database;


    public ClienteOperations(Context context)
    {
        dbHelper = new DbWraper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Cliente addCliente(String nome, String cpf, String telefone)
    {
        ContentValues values = new ContentValues();
        values.put(DbWraper.CLIENTE_NOME, nome);
        values.put(DbWraper.CLIENTE_CPF, cpf);
        values.put(DbWraper.CLIENTE_TELEFONE, telefone);
        long clientId = database.insert(DbWraper.CLIENTES, null, values);

        Cursor cursor = database.query(DbWraper.CLIENTES,
                CLIENT_TABLE_COLUMNS, DbWraper.CLIENTE_ID + " = "
                        + clientId, null, null, null, null );

        cursor.moveToFirst();

        Cliente cliente = parseCliente(cursor);
        cursor.close();

        return cliente;
    }

    public void delete(Cliente cliente){
        long id = cliente.getId();
        database.delete(DbWraper.CLIENTES, DbWraper.CLIENTE_ID + " = " + id, null);
    }

    public void deleteById(long id){
        database.delete(DbWraper.CLIENTES, DbWraper.CLIENTE_ID + " = " + id, null);
    }

    public List getAll() {
        List listClientes = new ArrayList<Cliente>();
        Cursor cursor = database.query(DbWraper.CLIENTES,
                CLIENT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cliente cliente = new Cliente();
            cliente = parseCliente(cursor);
            listClientes.add(cliente);
            cursor.moveToNext();
        }

        cursor.close();
        return listClientes;
    }

    private Cliente parseCliente(Cursor cursor){
        Cliente cliente = new Cliente();
        cliente.setId(cursor.getInt(0));
        cliente.setNome(cursor.getString(1));
        cliente.setCpf(cursor.getString(2));
        cliente.setTelefone(cursor.getString(3));
        return cliente;
    }
}
