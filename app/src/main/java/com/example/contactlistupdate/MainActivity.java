package com.example.contactlistupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText number;
    Button add;
    Button view;
    String cNAme;
    String cNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.contact_name);
        number=(EditText)findViewById(R.id.contact_number);
        add=findViewById(R.id.button);
        view=findViewById(R.id.button2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cNAme = name.getText().toString();
                cNumber = number.getText().toString();
                Intent int1 =new Intent(getApplicationContext(), mylist.class);
                int1.putExtra("cName",cNAme);
                int1.putExtra("cNumber",cNumber);

                Toast.makeText(MainActivity.this, cNumber, Toast.LENGTH_SHORT).show();
                startActivity(int1);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(), mylist.class);
                Toast.makeText(MainActivity.this, cNumber, Toast.LENGTH_SHORT).show();
                startActivity(int1);

            }
        });
    }
}