package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBrigade extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    FireBrigadeAdapter adapter;
    private ArrayList<LocationsGetter> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade);

        recyclerView=findViewById(R.id.firebrigaderecycle);
        reference= FirebaseDatabase.getInstance().getReference("Fire Brigade Locations");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter=new FireBrigadeAdapter(list,this);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    LocationsGetter locationsGetter=dataSnapshot.getValue(LocationsGetter.class);
                    list.add(locationsGetter);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(adapter);

    }
}