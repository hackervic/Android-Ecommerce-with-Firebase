package com.example.blood.ecom;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blood.ecom.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.HashMap;

public class KishanCallCentre extends AppCompatActivity {
    Button call,post;
    EditText editText;
DatabaseReference databaseReference;
    FirebaseAuth mauth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kishan_call_centre);

                call=(Button)findViewById(R.id.call);
                post=(Button)findViewById(R.id.post);
                editText=(EditText)findViewById(R.id.edt1);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse("tel:18001801551"));
                            if (callIntent.resolveActivity(getPackageManager()) != null) {

                                startActivity(callIntent);

                            }


                        }

                });

                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Users users=new Users();

                        String str = users.getPhone();
                        final String ed=editText.getText().toString();
                        databaseReference=FirebaseDatabase.getInstance().getReference().child("Queries");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                HashMap<String,Object> hashMap =new HashMap<>();
                                hashMap.put("query",ed);
                                databaseReference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(KishanCallCentre.this, "Posted successfully we will get back you soon", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(KishanCallCentre.this, "failed", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

    }
}
