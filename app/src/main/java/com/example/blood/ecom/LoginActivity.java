package com.example.blood.ecom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.ecom.Prevalent.prevalent;
import com.example.blood.ecom.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button logobtn;
    private EditText Iphone , Ipass;
    private ImageView loglogo;
    private TextView admin , not_admin , forget_password;
    private RelativeLayout rootlayout;
    private ProgressDialog pd;
   private CheckBox checkBox;
   private String parentDbname = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logobtn = findViewById(R.id.btn_login_login);
        Ipass = findViewById(R.id.edt_LOGIN_password);
        Iphone = findViewById(R.id.edt_LOGIN_phone);
       // loglogo = (ImageView)findViewById(R.id.login_applogo);
        admin = findViewById(R.id.tv_admin);
        not_admin = findViewById(R.id.tv_not_admin);
        forget_password = findViewById(R.id.tv_forget_password);
        checkBox = findViewById(R.id.remember_me);
        Paper.init(this);

        logobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
        Iphone.setText("+91");

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logobtn.setText("Admin Login");
                admin.setVisibility(View.INVISIBLE);
                not_admin.setVisibility(View.VISIBLE);
                parentDbname = "Admins";
            }
        });

        not_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logobtn.setText("Login");
                admin.setVisibility(View.VISIBLE);
                not_admin.setVisibility(View.INVISIBLE);
                parentDbname = "Users";
            }
        });

    }

    private void LoginUser()
    {

        String phone = Iphone.getText().toString();
        String password = Ipass.getText().toString();

        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password))
        {
            Toast.makeText(LoginActivity.this,"All Entries Are Required",Toast.LENGTH_SHORT).show();
        }
        else
        {
            AllowAccessTOUser( phone , password);

         //   Toast.makeText(LoginActivity.this,"Logging into account",Toast.LENGTH_SHORT).show();

        }
    }

    private void AllowAccessTOUser( final String phone, final String password) {

        if(checkBox.isChecked())
        {

            Paper.book().write(prevalent.UserPhonekey ,phone );
            Paper.book().write(prevalent.UserPasswordkey,password);

        }

       final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(parentDbname).child(phone).exists())
                {
                    Users users = dataSnapshot.child(parentDbname).child(phone).getValue(Users.class);
                    if(users.getPhone().equals(phone))
                    {
                        if( users.getPassword().equals(password)) {

                            if(parentDbname.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this,"Welcome to Admins Activity",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, Admin_CatagoryActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else if(parentDbname.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this,"Welcome To Users Activity",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                prevalent.currentOnlineUser = users;
                                startActivity(intent);
                                }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Phone Or Password is Incorrect",Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                else
                {

                    Toast.makeText(LoginActivity.this,"Accoutn Does not Exist",Toast.LENGTH_SHORT).show();

                    Toast.makeText(LoginActivity.this,"Please Creat New Account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
