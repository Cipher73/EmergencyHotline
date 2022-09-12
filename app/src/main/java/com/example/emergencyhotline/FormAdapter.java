package com.example.emergencyhotline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.MyViewHolder> {
    private ArrayList<Form> list ;
    Context context;

    public FormAdapter(ArrayList<Form> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FormAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.formview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormAdapter.MyViewHolder holder, int position) {
        Form form = list.get(position);

        holder.name.setText(form.getName());
        holder.dob.setText(form.getDob());
        holder.email.setText(form.getEmail());
        holder.height.setText(form.getHeight());
        holder.weight.setText(form.getWeight());
        holder.relationship.setText(form.getRelationship());
        holder.contact.setText(form.getContact());
        holder.e_contact.setText(form.getE_contact());
        holder.e_fName.setText(form.getE_fName());
        holder.address.setText(form.getAddress());
        holder.gender.setText(form.getSex());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView   name,address,contact,dob,gender,e_contact,e_fName,relationship,height,weight,email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.formname);
            address = itemView.findViewById(R.id.formaddress);
            email = itemView.findViewById(R.id.formemail);
            e_contact = itemView.findViewById(R.id.formecontact);
            e_fName = itemView.findViewById(R.id.formename);
            relationship = itemView.findViewById(R.id.formrelationship);
            height = itemView.findViewById(R.id.formheight);
            weight = itemView.findViewById(R.id.formweight);
            gender = itemView.findViewById(R.id.formgender);
            contact = itemView.findViewById(R.id.formcontact);
            dob = itemView.findViewById(R.id.formdob);



        }
    }
}
