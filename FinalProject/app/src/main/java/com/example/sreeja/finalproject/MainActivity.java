package com.example.sreeja.finalproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userName;
    private Button button;
    private EditText editUserName,editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);

        batteryLevel();



        }


    public void navigate(View view){
        editUserName= (EditText) findViewById(R.id.txtUname);
        editPassword= (EditText) findViewById(R.id.txtPwd);

         userName = editUserName.getText().toString();
        String password = editPassword.getText().toString();

        MySQLiteOpenHelper mySqLiteOpenHelper2 = new MySQLiteOpenHelper(this, null, null, 1);
        SQLiteDatabase sqLiteDatabase2 = mySqLiteOpenHelper2.getReadableDatabase();

        Cursor cursor = sqLiteDatabase2.query(MySQLiteOpenHelper.TABLE_NAME, null, null, null, null, null, null);

        int flag=0;

        String  dataUserName = null;
        String  dataPassword = null;
        String  dataUserText = null;

//        myList.clear();

        while(cursor.moveToNext())
        {
//            user = new UserDetails();

            dataUserName = cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLOUMN_DATA));
            dataPassword =  cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLOUMN_DATA1));

           /* user.setUserName(dataUserName);
            user.setPassword(dataPassword);
            user.setUserText(dataUserText);

            myList.add(user);*/

            if((userName.equals(dataUserName))&&(password.equals(dataPassword)))
            {

                flag=1;
            }
        }

        cursor.close();
        sqLiteDatabase2.close();

        if(flag==0)
        {

            {
                //toast="Invalid Credentials";
                Toast.makeText(getApplicationContext(), "Invalid Credentials",
                        Toast.LENGTH_LONG).show();
            }
        }

        else
        {
            Intent intent = new Intent(this,Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("userName",userName);
            startActivity(intent);
            editUserName.setText("");
            editPassword.setText("");


        }
    }


    private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                if(level<50){
                    Toast.makeText(getApplicationContext(), "Low Battery "+level, Toast.LENGTH_SHORT).show();

                }
            }
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }

    public void navigate2(View view){

            Intent intent = new Intent(this, Register.class);
            String mainValue= "mainActivity";
            intent.putExtra("fromMainActivity", mainValue);
            startActivity(intent);
        }



}
