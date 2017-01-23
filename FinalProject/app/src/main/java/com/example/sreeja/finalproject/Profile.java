package com.example.sreeja.finalproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sreeja on 4/22/2016.
 *
 */

public class Profile extends Fragment {

    public static Profile newInstance() {
        Profile fragment = new Profile();
        return fragment;
    }
    String latitude,longitude;

    private View view;
    private EditText et1,et2,et3;
    private Button button;
    private String userName;
    Main2Activity main2Activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.profile, container, false);
        userName=Main2Activity.getUserName();
        et1=(EditText)view.findViewById(R.id.et1);
        et2=(EditText)view.findViewById(R.id.et2);
        et3=(EditText)view.findViewById(R.id.et3);
        loadcontents();
        button=(Button)view. findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                et2.setEnabled(false);
                et3.setEnabled(false);
                String mail = et2.getText().toString();
                String phone = et3.getText().toString();
                if (mail.equals("") || mail == null || phone.equals("") || phone == null) {
                    Toast.makeText(getActivity(), "Please fill all your details ", Toast.LENGTH_SHORT).show();
                    et2.setEnabled(true);
                    et3.setEnabled(true);

                } else {
                    button.setEnabled(false);
                    MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
                    SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(MySQLiteOpenHelper.COLOUMN_DATA2, mail);
                    values.put(MySQLiteOpenHelper.COLOUMN_DATA3, phone);
                    sqLiteDatabase.update(MySQLiteOpenHelper.TABLE_NAME, values, MySQLiteOpenHelper.COLOUMN_DATA1 + "=?", new String[]{userName});
                    Toast.makeText(getActivity(), "Profile updated successfully ", Toast.LENGTH_SHORT).show();


                }
            }
        });

                return view;

    }

    private void loadcontents(){
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.query(MySQLiteOpenHelper.TABLE_NAME,new String[]{MySQLiteOpenHelper.COLOUMN_DATA2,MySQLiteOpenHelper.COLOUMN_DATA3,MySQLiteOpenHelper.COLOUMN_DATA4,MySQLiteOpenHelper.COLOUMN_DATA5},
                MySQLiteOpenHelper.COLOUMN_DATA1+"=?",new String[]{userName},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        String mail=cursor.getString(0);
        String phone=cursor.getString(1);
        latitude=cursor.getString(2);
        longitude=cursor.getString(3);
        et2.setText(mail);
        et3.setText(phone);
        et1.setText(userName);
        et1.setEnabled(false);
        et2.setEnabled(true);
        et3.setEnabled(true);


    }


    }

