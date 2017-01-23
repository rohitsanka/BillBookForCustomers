package com.example.sreeja.finalproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements LocationListener {
    private EditText et1,et2,et3,et4;

    private Button button;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private String latitude,longitude,username,password,mail,contact;
    double lat,lon;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerlayout);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText)findViewById(R.id.et4);


        button=(Button) findViewById(R.id.button);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000, 1, this);

    }
    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lon=location.getLongitude();
        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude();

    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }


    public void Register(View view){
         username=et1.getText().toString();
         password=et2.getText().toString();
         mail=et3.getText().toString();
         contact=et4.getText().toString();
         latitude=Double.toString(lat);
         longitude=Double.toString(lon);
        if(username.equals("")||username==null||password.equals("")||password==null||mail.equals("")||mail==null||contact.equals("")||contact==null){
            Toast.makeText(getBaseContext(), "Please fill all your details. ",
                    Toast.LENGTH_SHORT).show();
        }else {

            try{
                MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this, null, null, 1);
                SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
            String query = "insert into  " + MySQLiteOpenHelper.TABLE_NAME + "(" + MySQLiteOpenHelper.COLOUMN_DATA + "," + MySQLiteOpenHelper.COLOUMN_DATA1 + "," + MySQLiteOpenHelper.COLOUMN_DATA2 + "," + MySQLiteOpenHelper.COLOUMN_DATA3 + "," + MySQLiteOpenHelper.COLOUMN_DATA4 + "," + MySQLiteOpenHelper.COLOUMN_DATA5 + ") values" +
                    "('" + username + "','" + password + "','" + mail + "','" + contact + "','" + latitude + "','" + longitude + "')";
            sqLiteDatabase.execSQL(query);
                Toast.makeText(getApplicationContext(), "Details Saved",
                        Toast.LENGTH_LONG).show();
                sqLiteDatabase.close();

                et1.setText(null);
                et2.setText(null);
                et3.setText(null);
                et4.setText(null);



                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
           }
            catch(SQLiteConstraintException e){
                Toast.makeText(getApplicationContext(), "User already exists.", Toast.LENGTH_LONG).show();
                et1.setText(null);
                et2.setText(null);
                et3.setText(null);
                et4.setText(null);

            }


        }


    }

}
