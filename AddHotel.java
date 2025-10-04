package com.labproject.travelassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddHotel extends AppCompatActivity {
    private Spinner division;
    private Button addNewHotel;
    private TextInputLayout hotelName;
    private TextInputLayout hotelLoc;
    private TextInputLayout hotelRate;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root= db.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);


        addNewHotel=findViewById(R.id.newHotelAdd);
        hotelName= findViewById(R.id.newHotelName);
        hotelLoc= findViewById(R.id.newHotelLoc);
        hotelRate= findViewById(R.id.newHotelRate);
        division=findViewById(R.id.divisionChoose);


        String htlName= hotelName.getEditText().getText().toString();
        String htlLoc= hotelLoc.getEditText().getText().toString();
        String htlRate= hotelRate.getEditText().getText().toString();

        String[] DistrictNames= new String[]{"Bengaluru", "Mysore", "Darjeeling","Hyderabad","Chennai","Shillong","Jalpaiguri","Ranchi"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.division_view,R.id.textViewsampleId,DistrictNames);

        division.setAdapter(adapter);

        addNewHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String div= division.getSelectedItem().toString();
                if(valid()){
                    HashMap<String,Object> map= new HashMap<>();
                    map.put("hname",hotelName.getEditText().getText().toString());
                    map.put("loc",hotelLoc.getEditText().getText().toString());
                    map.put("rat",hotelRate.getEditText().getText().toString());
                    // root.child("Hotels").child(div).push().setValue(map);

                     
                    if(div.equals("Bengaluru")) root.child("Hotels").child("Bengaluru").push().setValue(map);
                    else if(div.equals("Mysore")) root.child("Hotels").child("Mysore").push().setValue(map);
                    else if(div.equals("Darjeeling")) root.child("Hotels").child("Darjeeling").push().setValue(map);
                    else if(div.equals("Hyderabad")) root.child("Hotels").child("Hyderabad").push().setValue(map);
                    else if(div.equals("Chennai")) root.child("Hotels").child("Chennai").push().setValue(map);
                    else if(div.equals("Shillong")) root.child("Hotels").child("Shillong").push().setValue(map);
                    else if(div.equals("Jalpaiguri")) root.child("Hotels").child("Jalpaiguri").push().setValue(map);
                    else if(div.equals("Ranchi")) root.child("Hotels").child("Ranchi").push().setValue(map);

                    hotelName.getEditText().setText("");
                    hotelLoc.getEditText().setText("");
                    hotelRate.getEditText().setText("");
                    Toast.makeText(getApplicationContext(), "Place added successfully.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(),"You can't leave any field empty",Toast.LENGTH_SHORT).show();

            }
        });
    }

    boolean valid(){
        String htlName= hotelName.getEditText().getText().toString();
        String htlLoc= hotelLoc.getEditText().getText().toString();
        String htlRate= hotelRate.getEditText().getText().toString();
        if(htlName.isEmpty()){
            hotelName.setError("Please enter Hotel name");
            hotelName.requestFocus();
            return false;
        }
        else if(htlLoc.isEmpty()){
            hotelLoc.setError("Please enter location");
            hotelLoc.requestFocus();
            return false;
        }
        else if(htlRate.isEmpty()){
            hotelRate.setError("Please enter rating");
            hotelRate.requestFocus();
            return false;
        }
        else{
            return true;
        }
    }
}