package com.example.sreeja.finalproject;

/**
 * Created by sreeja on 4/19/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SAMPLE";
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "DATA_Bill";
    private static final String COLOUMN_ID= "_id";
    public static final String COLOUMN_DATA="dataUserName";
    public static final String COLOUMN_DATA1="dataPassword";
    public static final String COLOUMN_DATA2="mailId";
    public static final String COLOUMN_DATA3="contactNo";
    public static final String COLOUMN_DATA4="latitude";
    public static final String COLOUMN_DATA5="longitude";
    public static final String BILL_TABLE="BILL_TABLE1";
    public static final String COLOUMN_ID_BILL="_id";
    public static final String COLOUMN_DATA_BILL_USERNAME="dataUserName";
    public static final String COLOUMN_DATA_BILL="dataTitle";
    public static final String COLOUMN_DATA_BILL_DES="dataDescription";


    public static final String COLOUMN_DATA1_BILL="dataImage";


    private static final String CREATE_TABLE = "create table "+TABLE_NAME+"("+COLOUMN_DATA+" text primary key,"+COLOUMN_DATA1+" text,"+COLOUMN_DATA2+" text,"+COLOUMN_DATA3+" text,"+COLOUMN_DATA4+" text,"+COLOUMN_DATA5+" text)";
    private static final String CREATE_TABLE_FOR_BILL = "create table "+BILL_TABLE+"("
            +COLOUMN_ID_BILL+" integer primary key autoincrement, "+COLOUMN_DATA_BILL_USERNAME+" text,"+COLOUMN_DATA_BILL+" text,"+COLOUMN_DATA_BILL_DES+" text,"+COLOUMN_DATA1_BILL+" text)";
    public MySQLiteOpenHelper(Context context, String name,
                              CursorFactory factory, int version) {

        super(context, DB_NAME, factory, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        try {
            arg0.execSQL(CREATE_TABLE);

        }
        catch(Exception e){}
        try {
            arg0.execSQL(CREATE_TABLE_FOR_BILL);
        } catch(Exception e){}



    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME );
        arg0.execSQL("DROP TABLE IF EXISTS " + BILL_TABLE);
        onCreate(arg0);
    }

}

