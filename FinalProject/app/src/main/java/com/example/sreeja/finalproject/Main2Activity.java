package com.example.sreeja.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity implements ViewBills.BillModify  {
    private DrawerLayout mDrawer;

    @Override
    public void modifyBill(BillModel billModel) {
        BillUpdate billUpdate=BillUpdate.newInstance(billModel);
        fragmentManager.beginTransaction().replace(R.id.flContent, billUpdate).commit();



    }

    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private static String latitude,longitude;

    private static String userName;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("userName");
        }

        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setupDrawerContent(nvDrawer);

    }
    public static String getUserName(){
        return userName;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);

        }
        return super.onOptionsItemSelected(item);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {

        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Profile profile=Profile.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, profile).commit();
                break;
            case R.id.nav_second_fragment:
                AddBill addBill=AddBill.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, addBill).commit();
                break;

            case R.id.nav_third_fragment:
                ViewBills viewBills=ViewBills.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, viewBills).commit();
                break;
            case R.id.nav_fourth_fragment:
                AboutUs aboutUs=AboutUs.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, aboutUs  ).commit();
                break;
            case R.id.nav_fifth_fragment:
                WayToUs wayToUs=WayToUs.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, wayToUs).commit();
                break;
            case R.id.nav_sixth_fragment:
                ContactInfo info=ContactInfo.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, info).commit();
                break;
            default:
                Profile profile1=Profile.newInstance();
                fragmentManager.beginTransaction().replace(R.id.flContent, profile1).commit();

        }



        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    }










