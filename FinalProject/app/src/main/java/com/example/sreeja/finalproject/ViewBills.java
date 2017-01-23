package com.example.sreeja.finalproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sreeja on 4/26/2016.
 */
public class ViewBills extends Fragment{
    private View view;
    private ListView bills_list;
    private BillModify modify;

    ArrayList<BillModel> billsList = new ArrayList<>();

    private String userName,billTitle,billDescription;
    private String encodedImage;
    Context context;
    String name=Main2Activity.getUserName();
    public static ViewBills newInstance() {
        ViewBills fragment = new ViewBills();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.view_bills_layout, container, false);

        bills_list = (ListView) view.findViewById(R.id.bills_list);
        context = getActivity();

        MySQLiteOpenHelper mySqLiteOpenHelper2 = new MySQLiteOpenHelper(getActivity(), null, null, 1);
        SQLiteDatabase sqLiteDatabase2 = mySqLiteOpenHelper2.getReadableDatabase();

        Cursor cursor = sqLiteDatabase2.query(MySQLiteOpenHelper.BILL_TABLE, new String[]{MySQLiteOpenHelper.COLOUMN_DATA_BILL,MySQLiteOpenHelper.COLOUMN_DATA_BILL_DES,MySQLiteOpenHelper.COLOUMN_DATA1_BILL},MySQLiteOpenHelper.COLOUMN_DATA_BILL_USERNAME +"=?", new String[]{name}, null, null, null, null);
        while(cursor.moveToNext()){
            BillModel bill = new BillModel();
            userName = name;
            billTitle = cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLOUMN_DATA_BILL));
            billDescription = cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLOUMN_DATA_BILL_DES));

            encodedImage =  cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.COLOUMN_DATA1_BILL));
            bill.setUserName(userName);
            bill.setBillTitle(billTitle);
            bill.setBillDescription(billDescription);
            bill.setEncodedImage(encodedImage);
            billsList.add(bill);
        }
        bills_list.setAdapter(new BillsAdapter(billsList,context));





        bills_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                BillModel billModel = billsList.get(arg2);
                if (getActivity() instanceof BillModify) {
                    modify = (BillModify) getActivity();
                } else {
                    throw new ClassCastException();
                }


                modify.modifyBill(billModel);


            }
        });

        return view;

    }


    public interface BillModify{
        public void modifyBill(BillModel billModel);

    }


}
