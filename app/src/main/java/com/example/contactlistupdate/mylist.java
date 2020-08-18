package com.example.contactlistupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mylist extends AppCompatActivity {
    RecyclerView rc;
    RecyclerView.LayoutManager lm;
    contactAdapter adapter;
    String contact_name="default Name";
    String contact_number="default Number";
    Button btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);



        rc = findViewById(R.id.rc_view);
        btn = (Button) findViewById(R.id.btn);
        lm = new LinearLayoutManager(this);
        rc.setLayoutManager(lm);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i3);

            }
        });



            try {
                Intent int2 = getIntent();
                contact_name = int2.getStringExtra("cName");
                contact_number = int2.getStringExtra("cNumber");
                Contact c =new Contact(contact_name,contact_number);

                MyDbHandler dbHandler =new MyDbHandler(this);
                dbHandler.addContact(c);

                List<Contact> contactList=new ArrayList<>();

                contactList= dbHandler.getAllContacts();

                for (int i=0;i<=contactList.size();i++){

                    Contact cont;
                    cont = contactList.get(i);
                    adapter = new contactAdapter(cont,this);

                    Log.d("dbHarry",String.valueOf(contactList.get(i).getPhoneNumber()));
                }




            rc.setAdapter(adapter);


            }

            catch (Exception e) {
            Toast.makeText(this, "no data available", Toast.LENGTH_SHORT).show();
        }
        }
    }