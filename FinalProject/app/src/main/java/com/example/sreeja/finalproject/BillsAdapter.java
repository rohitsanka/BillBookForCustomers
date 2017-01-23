package com.example.sreeja.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nishith on 4/25/2016.
 */
public class BillsAdapter extends BaseAdapter {

    ArrayList<BillModel> billsList = new ArrayList<>();
    Context mContext;
    LayoutInflater inflater;

    public BillsAdapter(ArrayList<BillModel> billsList, Context mContext) {
        super();
        this.billsList = billsList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(this.mContext);

    }

    @Override
    public int getCount() {
        return billsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bill_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        BillModel bill = billsList.get(position);
        Bitmap b = getBitmapFromString(billsList.get(position).getEncodedImage());
        mViewHolder.userName.setText(bill.getUserName());
        mViewHolder.billtitle.setText(bill.getBillTitle());
        mViewHolder.billDescription.setText(bill.getBillDescription());


        mViewHolder.billImage.setImageBitmap(b);

        return convertView;
    }

    private class MyViewHolder {
        TextView userName;
        ImageView billImage;
        TextView billtitle;
        TextView billDescription;

        public MyViewHolder(View item) {
            userName = (TextView) item.findViewById(R.id.userName);
            billtitle=(TextView)item.findViewById(R.id.billTitle);
            billDescription=(TextView)item.findViewById(R.id.billDescription);
            billImage = (ImageView) item.findViewById(R.id.billImage);
        }
    }


        public Bitmap getBitmapFromString(String encodedString) {
            byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;

        }

}


