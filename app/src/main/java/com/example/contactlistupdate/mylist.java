package com.example.contactlistupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mylist extends AppCompatActivity {
    RecyclerView rc;
    RecyclerView.LayoutManager lm;
    contactAdapter adapter;
    String contact_name;
    String contact_number;
    Button btn;
    MyDbHandler dbHandler;
    List<Contact> contactList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        rc = findViewById(R.id.rc_view);
        btn = (Button) findViewById(R.id.btn);

        dbHandler =new MyDbHandler(getApplicationContext());
        lm = new LinearLayoutManager(this);
        rc.setLayoutManager(lm);
        contactList= dbHandler.getAllContacts();
        adapter = new contactAdapter(contactList);
        rc.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i3);

                try {
                    Intent int2 = getIntent();
                    contact_name = int2.getStringExtra("cName");
                    contact_number = int2.getStringExtra("cNumber");
                    Contact c =new Contact(contact_name,contact_number);

                    dbHandler.addContact(c);
                    contactList.add(c);

                    adapter.notifyItemInserted(contactList.size()-1);
                    Toast.makeText(getApplicationContext(),contact_name+","+contact_number,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"item added",Toast.LENGTH_LONG).show();

                }

                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "no data available", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}