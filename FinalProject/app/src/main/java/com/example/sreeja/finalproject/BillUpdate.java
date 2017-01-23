package com.example.sreeja.finalproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by sreeja on 4/28/2016.
 */
public class BillUpdate extends Fragment {
    private View view;
    private BillModel data;
    private EditText et2;
    private TextView user,et1;
    private ImageView imageUploaded;
    private Button edit,delete;
    private String name,title,description,image;
    public static BillUpdate newInstance(BillModel billModel) {
        BillUpdate fragment = new BillUpdate();
        Bundle args=new Bundle();
        args.putSerializable("bill", (Serializable) billModel);

        fragment.setArguments(args);
        return fragment;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        data= (BillModel) args.getSerializable("bill");
        name=data.getUserName();
        title=data.getBillTitle();
        description=data.getBillDescription();
        image=data.getEncodedImage();



        view = inflater.inflate(R.layout.bill_update, container, false);
        et1 = (TextView) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        user=(TextView)view.findViewById(R.id.userName);
        edit=(Button)view.findViewById(R.id.edit);
        delete=(Button)view.findViewById(R.id.delete);


        imageUploaded = (ImageView) view.findViewById(R.id.billImage);
        Bitmap b = getBitmapFromString(image);

        et1.setText(title);
        et2.setText(description);
        et2.setEnabled(true);
        user.setText("Hello "+name);
        imageUploaded.setImageBitmap(b);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });





        return view;
    }


    public void edit() {
        et2.setEnabled(false);
        String description = et2.getText().toString();
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteOpenHelper.COLOUMN_DATA_BILL_DES, description);
        sqLiteDatabase.update(MySQLiteOpenHelper.BILL_TABLE, values, MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME + "=?"+ " and "  +MySQLiteOpenHelper.COLOUMN_DATA_BILL+"=?", new String[]{name,title});

        edit.setEnabled(false);

        Toast.makeText(getActivity(), "Bill updated successfully ", Toast.LENGTH_SHORT).show();


    }



    public void delete(){
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
/*
        sqLiteDatabase.execSQL( "delete from "+MySQLiteOpenHelper.BILL_TABLE+"WHERE" +MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME+"="+name+"And"+MySQLiteOpenHelper.COLOUMN_DATA_BILL+"="+title);
*/
// Specify arguments in placeholder order.
// Issue SQL statement.
        sqLiteDatabase.delete(MySQLiteOpenHelper.BILL_TABLE,MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME+ "= ? AND "+MySQLiteOpenHelper.COLOUMN_DATA_BILL+ "=?", new String[]{name,title});
        //String where = MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME + "=" + "?"+"and"+MySQLiteOpenHelper.COLOUMN_DATA_BILL+"="+"?";
      //  sqLiteDatabase.delete(MySQLiteOpenHelper.BILL_TABLE, where,  new String[]{name,title});
       /* String query = "DELETE FROM "+MySQLiteOpenHelper.BILL_TABLE+"WHERE"+ MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME+"name"+ " and "  +MySQLiteOpenHelper.COLOUMN_DATA_BILL+"'name'";
        sqLiteDatabase.execSQL(query);*/

        //String query = "DELETE FROM '"+MySQLiteOpenHelper.TABLE_NAME+"'WHERE dataUserName='"+userName+"'";
       /* (TABLE_NAME,
        TABLE_COLUMN_ONE + " = ? AND " + TABLE_COLUMN_TWO + " = ?",
                new String[] {dan, vrijeme+""}*/
       // String query = "DELETE FROM '"+MySQLiteOpenHelper.BILL_TABLE+"'WHERE '"+MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME+"'='"+name+"'and'"+MySQLiteOpenHelper.COLOUMN_DATA_BILL+"'='"+title+"'";
        //sqLiteDatabase.execSQL(query);
        Toast.makeText(getActivity(), "Bill deleted successfully ", Toast.LENGTH_SHORT).show();
        et1.setText("");
        et2.setText("");
        Bitmap b=null;
        et2.setEnabled(false);
        imageUploaded.setImageBitmap(b);
        edit.setEnabled(false);
        delete.setEnabled(false);

    }

    public Bitmap getBitmapFromString(String encodedString) {
        byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }
    }
