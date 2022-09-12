package com.example.emergencyhotline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FireBrigadeAdapter extends RecyclerView.Adapter<FireBrigadeAdapter.MyViewHolder> {

    private ArrayList<LocationsGetter> list ;
    private Context context;

    public FireBrigadeAdapter(ArrayList<LocationsGetter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FireBrigadeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.firebrigade_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FireBrigadeAdapter.MyViewHolder holder, int position) {
        LocationsGetter locations = list.get(position);
        holder.service.setText(locations.getService());
        holder.address.setText(locations.getAddress());
        holder.latitude.setText(locations.getLatitude());
        holder.longitude.setText(locations.getLongitude());
        holder.country.setText(locations.getCountry());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView address,longitude,latitude,country,service;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.fAddress);
            longitude = itemView.findViewById(R.id.fLongitude);
            latitude = itemView.findViewById(R.id.fLatitude);
            country = itemView.findViewById(R.id.fCountry);
            service = itemView.findViewById(R.id.fService);


        }
    }
    }
