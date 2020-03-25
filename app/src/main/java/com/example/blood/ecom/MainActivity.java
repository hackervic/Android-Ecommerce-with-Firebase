package com.example.blood.ecom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.blood.ecom.Prevalent.prevalent;
import com.example.blood.ecom.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private ImageView applogo;
    private Button btnlogin , btnregister;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




       Paper.init(this);
       btnlogin = findViewById(R.id.register_btn);
       btnregister = findViewById(R.id.btn_login);
    progressDialog = new ProgressDialog(this);
       btnlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,Register_Activity.class));

           }
       });
       btnregister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,LoginActivity.class);
               startActivity(intent);
           }
       });



       String userphone = Paper.book().read(prevalent.UserPhonekey);
        String userpassword = Paper.book().read(prevalent.UserPasswordkey);


        if(userphone != "" && userpassword != "")
        {
            if(!TextUtils.isEmpty(userphone) && !TextUtils.isEmpty(userpassword))
            {
                AllowAccess(userphone , userpassword);
                progressDialog.setTitle("Already Logged In");
                progressDialog.setMessage("Welcome to The Sasta");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        }

    }

    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child(phone).exists())
                {
                    Users users = dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if(users.getPhone().equals(phone))
                    {

                        if( users.getPassword().equals(password)) {

                                Toast.makeText(MainActivity.this,"Planning Begins Here ): ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            prevalent.currentOnlineUser = users;
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Phone Or Password is Incorrect",Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this,"user"+phone+"not exist",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
