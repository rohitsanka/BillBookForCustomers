package com.example.sreeja.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sreeja on 4/22/2016.
 */
public class WayToUs extends Fragment {

    private TextView et1;
    private Button button;
    private View view;
    String userName;
    private double lat,lon;

    public static WayToUs newInstance() {
        WayToUs fragment = new WayToUs();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.waytous, container, false);
        userName = Main2Activity.getUserName();
        et1=(TextView)view.findViewById(R.id.et1);
        button=(Button)view.findViewById(R.id.button);
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        String userName=Main2Activity.getUserName();
        Cursor cursor=sqLiteDatabase.query(MySQLiteOpenHelper.TABLE_NAME,new String[]{MySQLiteOpenHelper.COLOUMN_DATA2,MySQLiteOpenHelper.COLOUMN_DATA3,MySQLiteOpenHelper.COLOUMN_DATA4,MySQLiteOpenHelper.COLOUMN_DATA5},
                MySQLiteOpenHelper.COLOUMN_DATA1+"=?",new String[]{userName},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        String mail=cursor.getString(0);
        String phone=cursor.getString(1);
        String latitude=cursor.getString(2);
        String longitude=cursor.getString(3);
         lat=Double.parseDouble(latitude);
         lon=Double.parseDouble(longitude);


        Location loc1 = new Location("");
        loc1.setLatitude(lat);
        loc1.setLongitude(lon);

        Location loc2 = new Location("");
        loc2.setLatitude(38.9285323);
        loc2.setLongitude(-94.4004662);

        float distanceInMeters = loc1.distanceTo(loc2);
        et1.setText("Hi"+userName+" you are just "+distanceInMeters+" meters from us." +" Expecting your arrival soon." );

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+lat+" "+lon+" &daddr=38.9285323,-94.4004662"));
                startActivity(intent);

            }


        });               return view;

    }
    }

