package com.example.emergencyhotline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataBaseView extends AppCompatActivity {
    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    DatabaseReference reference;
    userAdapter adapter;
    private ArrayList<UserClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_view);
        mAuth = FirebaseAuth.getInstance();

        recyclerView=findViewById(R.id.userrecyler);
        reference= FirebaseDatabase.getInstance().getReference("Users");

        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();

        adapter=new userAdapter(list,this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                   UserClass user=dataSnapshot.getValue(UserClass.class);
                   list.add(user);
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