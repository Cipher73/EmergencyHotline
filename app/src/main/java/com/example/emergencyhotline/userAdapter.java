package com.example.emergencyhotline;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    private ArrayList<UserClass>list;
    Context context;

    public userAdapter(ArrayList<UserClass> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override

    public userAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapter.MyViewHolder holder, int position) {

        UserClass user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.id.setText(user.getId());
        holder.isAdmin.setText(user.getIsAdmin());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder id_builder = new AlertDialog.Builder(context);
                id_builder.setTitle("Enter ID user");
                final EditText text = new EditText(context);
                text.setInputType(InputType.TYPE_CLASS_TEXT);
                id_builder.setView(text);

                id_builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String ID = text.getText().toString().trim();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("DELETE USER");
                        builder.setMessage("Are you sure?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("Users").child(ID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context, "user has been deleted from database", Toast.LENGTH_LONG).show();
                                        context.startActivity(new Intent(context,Adminhome.class));
                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "user not deleted", Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.show();


                    }
                }); id_builder.show();


            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name,id,isAdmin,phone,email;
        private Button button ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.username);
            id =itemView.findViewById(R.id.userID);
            button=itemView.findViewById(R.id.btnDeleteUser);
            email =itemView.findViewById(R.id.useremail);
            isAdmin=itemView.findViewById(R.id.isAdmin);

        }
    }
}
