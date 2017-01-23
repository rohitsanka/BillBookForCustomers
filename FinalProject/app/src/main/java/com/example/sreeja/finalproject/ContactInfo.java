package com.example.sreeja.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sreeja.finalproject.R;


public class ContactInfo extends Fragment {

    ImageView google_img , phone_img, mail_img;


    // TODO: Rename and change types and number of parameters
    public static ContactInfo newInstance() {
        ContactInfo fragment = new ContactInfo();
        return fragment;
    }

    public ContactInfo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.activity_contact_info, container,
                false);

        google_img =(ImageView)rootView.findViewById(R.id.imageView);
        phone_img =(ImageView)rootView.findViewById(R.id.phone_contact);
        mail_img =(ImageView)rootView.findViewById(R.id.email_contact);
        google_img.setClickable(true);
        phone_img.setClickable(true);
        mail_img.setClickable(true);

        Toast.makeText(getActivity(), "click on the icons for action", Toast.LENGTH_SHORT).show();

        mail_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "rxs45550@ucmo.edu", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject:");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        phone_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent phone_intent =new Intent(Intent.ACTION_CALL);
                phone_intent.setData(Uri.parse("tel:8167169610"));
                startActivity(phone_intent);

            }
        });
        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Google_intent = new Intent(Intent.ACTION_VIEW);
                Google_intent.setData(Uri.parse("geo:University of Central Missouri, Chipman Road, Lee Summit, MO?q=University of Central Missouri, Chipman Road, Lee Summit, MO"));
                //Google_intent.setFlags(R.drawable.abc_btn_check_material);
                Intent choose_app_type =Intent.createChooser(Google_intent,"Launch Maps");
                startActivity(choose_app_type);

            }
        });




        return rootView;
    }

}
