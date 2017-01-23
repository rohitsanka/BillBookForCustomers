package com.example.sreeja.finalproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sreeja on 4/26/2016.
 */
public class AddBill extends Fragment {
    public static AddBill newInstance() {
        AddBill fragment = new AddBill();
        return fragment;
    }
    private EditText et1,et2;
    private TextView user;
    private Button takePhotoBtn,save;
    private static final int CAMERA_OR_IMAGE_REQUEST = 1888;
     private ImageView imageUploaded;
    private String userName="";
    private String billTitle="";
    private String billDescription="";
    String encodedImage="";
    Bitmap photo;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.add_bill_layout, container, false);
        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);
        user=(TextView) view.findViewById(R.id.userName);
        userName=Main2Activity.getUserName();
        user.setText("Hello "+userName);


        imageUploaded = (ImageView) view.findViewById(R.id.imageUploaded);
        takePhotoBtn = (Button) view.findViewById(R.id.button);
        save = (Button) view.findViewById(R.id.button1);

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageIntent();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();



            }
        });


        return view;
    }


    private void openImageIntent() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_OR_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_OR_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK) {
            if(data.getData()!=null){
                Uri selectedImageUri;
                selectedImageUri = data == null ? null : data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    imageUploaded.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                photo = (Bitmap) data.getExtras().get("data");
                imageUploaded.setImageBitmap(photo);

            }
        }
    }

    public  void saveData(){
        billTitle = et1.getText().toString();
        billDescription=et2.getText().toString();
        if(billTitle.equals("")|| billTitle ==null||billDescription.equals("")||billDescription ==null||photo==null){
            if(billTitle.equals("")|| billTitle ==null||billDescription.equals("")||billDescription ==null)
            Toast.makeText(getActivity(), "Please enter all details ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Please upload a photo ", Toast.LENGTH_SHORT).show();


        }
        else {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

            MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), null, null, 1);
            SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
            String query = "insert into " + MySQLiteOpenHelper.BILL_TABLE + "(" + MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME + "," + MySQLiteOpenHelper.COLOUMN_DATA_BILL + "," + MySQLiteOpenHelper.COLOUMN_DATA_BILL_DES + "," + MySQLiteOpenHelper.COLOUMN_DATA1_BILL + ") values" +
                    "('" + userName + "','" + billTitle + "','" + billDescription + "','" + encodedImage + "')";
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.close();
            et1.setText("");
            et2.setText("");
            Bitmap b=null;
            imageUploaded.setImageBitmap(b);

            Toast.makeText(getActivity(), "Bill added successfully ", Toast.LENGTH_SHORT).show();

        }

    }
}
