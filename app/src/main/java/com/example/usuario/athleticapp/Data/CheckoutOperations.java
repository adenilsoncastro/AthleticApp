package com.example.usuario.athleticapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.usuario.athleticapp.Model.Checkout;
import com.example.usuario.athleticapp.Model.Cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckoutOperations {
    private DbWraper dbHelper;
    private String[] CHECKOUT_TABLE_COLUMNS = {DbWraper.CHECKOUT_ID, DbWraper.CHECKOUT_NOME, DbWraper.CHECKOUT_PRECO, DbWraper.CHECKOUT_DATA};
    private SQLiteDatabase database;

    public CheckoutOperations(Context context)
    {
        dbHelper = new DbWraper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Checkout addCheckout(Checkout checkout)
    {
        ContentValues values = new ContentValues();
        values.put(DbWraper.CHECKOUT_NOME, checkout.getNomeCliente());
        values.put(DbWraper.CHECKOUT_PRECO, checkout.getValor());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        String dateToFormat = checkout.getData().toString();

        try {
            date = format.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        values.put(DbWraper.CHECKOUT_DATA, timeStamp);

        long checkoutId = database.insert(DbWraper.CHECKOUTS, null, values);

        Cursor cursor = database.query(DbWraper.CHECKOUTS,
                CHECKOUT_TABLE_COLUMNS, DbWraper.CHECKOUT_ID + " = "
                        + checkoutId, null, null, null, null );

        cursor.moveToFirst();

        Checkout chkNew = parseCheckout(cursor);
        cursor.close();

        return chkNew;
    }

    public void delete(Cliente cliente){
        long id = cliente.getId();
        database.delete(DbWraper.CHECKOUTS, DbWraper.CHECKOUT_ID+ " = " + id, null);
    }

    public List getAll() {
        List listCheckouts = new ArrayList<Checkout>();
        Cursor cursor = database.query(DbWraper.CHECKOUTS,
                CHECKOUT_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Checkout checkout = new Checkout();
            checkout = parseCheckout(cursor);
            listCheckouts.add(checkout);
            cursor.moveToNext();
        }

        cursor.close();
        return listCheckouts;
    }

    private Checkout parseCheckout(Cursor cursor){
        Checkout checkout = new Checkout();
        checkout.setId(cursor.getInt(0));
        checkout.setNomeCliente(cursor.getString(1));
        checkout.setValor(cursor.getDouble(2));

        String data = cursor.getString(3);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = dateFormat.parse(data);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        checkout.setData(d);
        //checkout.setData(new Date(cursor.getLong(3)*1000));
        return checkout;
    }
}