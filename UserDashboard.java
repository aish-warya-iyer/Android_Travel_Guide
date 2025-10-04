package com.labproject.travelassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView button; //current location image
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon,hotels,restaurants,malls,spots;
    FirebaseAuth firebaseAuth;
    Spinner division;
    //String selectedDiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        button=findViewById(R.id.nearby_button); //image view
        drawerLayout= findViewById(R.id.drawer_layout);
        menuIcon=findViewById(R.id.menu_icon);
        hotels=findViewById(R.id.hotels);
        restaurants=findViewById(R.id.restaurants);
        malls=findViewById(R.id.malls);
        spots=findViewById(R.id.spots);
        firebaseAuth=FirebaseAuth.getInstance();

        navigationDrawer ();
        navigationView= findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        //t-spinner
        division=findViewById(R.id.divisionChooseUser);
        String[] DistrictNames= new String[]{"Bengaluru", "Mysore", "Darjeeling",
                "Hyderabad","Chennai","Shillong",
                "Jalpaiguri","Ranchi"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.division_view,R.id.textViewsampleId,DistrictNames);
        division.setAdapter(adapter);
        String div= division.getSelectedItem().toString();


        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDiv = null;
                String div= division.getSelectedItem().toString();


                if(div.equals("Bengaluru")) selectedDiv="Bengaluru";
                else if(div.equals("Mysore")) selectedDiv="Mysore";
                else if(div.equals("Darjeeling")) selectedDiv="Darjeeling";
                else if(div.equals("Hyderabad")) selectedDiv="Hyderabad";
                else if(div.equals("Chennai")) selectedDiv="Chennai";
                else if(div.equals("Shillong")) selectedDiv="Shillong";
                else if(div.equals("Jalpaiguri")) selectedDiv="Jalpaiguri";
                else if(div.equals("Ranchi")) selectedDiv="Ranchi";

                Intent intent=new Intent(UserDashboard.this,RecyclerviewHtl.class);
                intent.putExtra("tag",selectedDiv);
                startActivity(intent);
            }
        });
        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedDiv = null;
                String div= division.getSelectedItem().toString();


                if(div.equals("Bengaluru")) selectedDiv="Bengaluru";
                else if(div.equals("Mysore")) selectedDiv="Mysore";
                else if(div.equals("Darjeeling")) selectedDiv="Darjeeling";
                else if(div.equals("Hyderabad")) selectedDiv="Hyderabad";
                else if(div.equals("Chennai")) selectedDiv="Chennai";
                else if(div.equals("Shillong")) selectedDiv="Shillong";
                else if(div.equals("Jalpaiguri")) selectedDiv="Jalpaiguri";
                else if(div.equals("Ranchi")) selectedDiv="Ranchi";

                Intent intent=new Intent(UserDashboard.this,RecyclerviewRes.class);
                intent.putExtra("tag",selectedDiv);
                startActivity(intent);
            }
        });
        malls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDiv = null;
                String div= division.getSelectedItem().toString();


                if(div.equals("Bengaluru")) selectedDiv="Bengaluru";
                else if(div.equals("Mysore")) selectedDiv="Mysore";
                else if(div.equals("Darjeeling")) selectedDiv="Darjeeling";
                else if(div.equals("Hyderabad")) selectedDiv="Hyderabad";
                else if(div.equals("Chennai")) selectedDiv="Chennai";
                else if(div.equals("Shillong")) selectedDiv="Shillong";
                else if(div.equals("Jalpaiguri")) selectedDiv="Jalpaiguri";
                else if(div.equals("Ranchi")) selectedDiv="Ranchi";

                Intent intent=new Intent(UserDashboard.this,RecyclerviewMall.class);
                intent.putExtra("tag",selectedDiv);
                startActivity(intent);
            }
        });
        spots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDiv = null;
                String div= division.getSelectedItem().toString();


                if(div.equals("Bengaluru")) selectedDiv="Bengaluru";
                else if(div.equals("Mysore")) selectedDiv="Mysore";
                else if(div.equals("Darjeeling")) selectedDiv="Darjeeling";
                else if(div.equals("Hyderabad")) selectedDiv="Hyderabad";
                else if(div.equals("Chennai")) selectedDiv="Chennai";
                else if(div.equals("Shillong")) selectedDiv="Shillong";
                else if(div.equals("Jalpaiguri")) selectedDiv="Jalpaiguri";
                else if(div.equals("Ranchi")) selectedDiv="Ranchi";
                Intent intent=new Intent(UserDashboard.this,RecyclerviewSpot.class);
                intent.putExtra("tag",selectedDiv);
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(UserDashboard.this,NearbySearch.class);
                startActivity(intent);
            }
        });
    }

    private void navigationDrawer() {
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if(item.getItemId()==R.id.nav_nearby_search) {
            intent = new Intent(UserDashboard.this, NearbySearch.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.nav_admin) {
            startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
        }
        else if(item.getItemId()==R.id.emergency) {
            intent = new Intent(UserDashboard.this, Emergency.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.nav_profile){
            intent = new Intent(UserDashboard.this, Profile.class);
            startActivity(intent);
        }

        else if (item.getItemId()==R.id.nav_logout){
            firebaseAuth.signOut();
            intent = new Intent(UserDashboard.this, Login_form.class);
            startActivity(intent);
            finish();
        }
        return false;
    }


}