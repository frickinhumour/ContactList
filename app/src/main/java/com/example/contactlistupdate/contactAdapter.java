package com.example.contactlistupdate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ContactAdapterVH> {



    Activity thisActivity;
    List<Contact> contacts= new ArrayList<>();

    public contactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }


    public class ContactAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;
        LinearLayout linearLayout;
        TextView txt_number;

        public ContactAdapterVH(@NonNull View itemView) {
            super(itemView);
            txt_name= itemView.findViewById(R.id.person_name);
            txt_number=itemView.findViewById(R.id.person_number);
            linearLayout=itemView.findViewById(R.id.contact_layout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Intent intent;

            if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)view.getContext(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        0);
                intent = new Intent(Intent.ACTION_CALL, Uri.parse(contacts.get(getAdapterPosition()).getPhoneNumber()));
            }
            else
            {
                intent = new Intent(Intent.ACTION_CALL, Uri.parse(contacts.get(getAdapterPosition()).getPhoneNumber()));
            }
            view.getContext().startActivity(intent);


        }
    }


    @NonNull
    @Override
    public ContactAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V= (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,parent,false);
        ContactAdapterVH vh= new ContactAdapterVH(V);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final ContactAdapterVH holder, final int position) {
        Contact c= contacts.get(position);
        holder.txt_name.setText(c.getName());
        holder.txt_number.setText(c.getPhoneNumber());

        Log.d("contact_cur",contacts.get(position).getName()+":"+contacts.get(position).getPhoneNumber());

    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }
}

