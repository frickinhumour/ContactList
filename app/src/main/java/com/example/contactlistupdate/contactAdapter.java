package com.example.contactlistupdate;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ContactAdapterVH> {


    String cname;
    String cnumber;
    Activity thisActivity;
    List<String> name =new ArrayList<String>();
    List<String> number =new ArrayList<String>();
//    public String getCname(ArrayList <String> arrayname){
//      String name = arrayname()
//
//        return name;
//    };



    public contactAdapter(String name, String number, Activity thisActivity){
        this.cname=name;
        this.cnumber=number;
        this.name.add(cname);
        this.number.add(cnumber);
        this.thisActivity=thisActivity;
    }



    public class ContactAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name;
        TextView txt_number;
        LinearLayout linearLayout;

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
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number.get(getAdapterPosition())));
                }
                else
                {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number.get(getAdapterPosition())));
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
        holder.txt_name.setText(name.get(position));
        holder.txt_number.setText(number.get(position));

    }


    @Override
    public int getItemCount() {
        return name.size();
    }
}

