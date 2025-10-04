package com.labproject.travelassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddShop extends AppCompatActivity {

    private Spinner division;
    private Button addNewShop;
    private TextInputLayout shopName;
    private TextInputLayout shopLoc;

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root= db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        addNewShop=findViewById(R.id.newShopAdd);
        shopName= findViewById(R.id.newShopName);
        shopLoc= findViewById(R.id.newShopLoc);

        division=findViewById(R.id.divisionChooseShop);

        String[] DistrictNames= new String[]{"Bengaluru", "Mysore", "Darjeeling","Hyderabad","Chennai","Shillong","Jalpaiguri","Ranchi"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.division_view,R.id.textViewsampleId,DistrictNames);

        division.setAdapter(adapter);

        addNewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String div= division.getSelectedItem().toString();
                if(valid()){
                    HashMap<String,Object> map= new HashMap<>();
                    map.put("sname",shopName.getEditText().getText().toString());
                    map.put("loc",shopLoc.getEditText().getText().toString());

                    //root.child("Shops").child(div).push().setValue(map);

                    if(div.equals("Bengaluru")) root.child("Shops").child("Bengaluru").push().setValue(map);
                    else if(div.equals("Mysore")) root.child("Shops").child("Mysore").push().setValue(map);
                    else if(div.equals("Darjeeling")) root.child("Shops").child("Darjeeling").push().setValue(map);
                    else if(div.equals("Hyderabad")) root.child("Shops").child("Hyderabad").push().setValue(map);
                    else if(div.equals("Chennai")) root.child("Shops").child("Chennai").push().setValue(map);
                    else if(div.equals("Shillong")) root.child("Shops").child("Shillong").push().setValue(map);
                    else if(div.equals("Jalpaiguri")) root.child("Shops").child("Jalpaiguri").push().setValue(map);
                    else if(div.equals("Ranchi")) root.child("Shops").child("Ranchi").push().setValue(map);

                    shopName.getEditText().setText("");
                    shopLoc.getEditText().setText("");
                    Toast.makeText(getApplicationContext(), "Place added successfully.", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(AddShop.this,"You can't leave any field empty",Toast.LENGTH_SHORT).show();

            }
        });

    }

    boolean valid(){
        String shpName= shopName.getEditText().getText().toString();
        String shpLoc= shopLoc.getEditText().getText().toString();

        if(shpName.isEmpty()){
            shopName.setError("Please enter Shop name");
            shopName.requestFocus();
            return false;
        }
        else if(shpLoc.isEmpty()){
            shopLoc.setError("Please enter location");
            shopLoc.requestFocus();
            return false;
        }
        else return true;
    }
}