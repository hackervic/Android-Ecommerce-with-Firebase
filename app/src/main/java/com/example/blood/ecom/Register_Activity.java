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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Register_Activity extends AppCompatActivity {

    DatabaseReference Rootref;
    private ImageView reslogo;
    public EditText name , phone_num , password;
    private Button createaccount;
    private ProgressDialog progressDialog;
    private TextView al;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

      //  reslogo = (ImageView)findViewById(R.id.register_applogo);
        name = findViewById(R.id.register_name);
        phone_num = findViewById(R.id.edt_register_phone);
        password = findViewById(R.id.edt_register_password);
        progressDialog = new ProgressDialog(this);
        createaccount = findViewById(R.id.btn_creat_account);
        firebaseDatabase = FirebaseDatabase.getInstance();
        Rootref  = FirebaseDatabase.getInstance().getReference();
        al=(TextView)findViewById(R.id.alrady);

        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAcoount();

            }
        });
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(Register_Activity.this,LoginActivity.class);
                mintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mintent);
            }
        });


    }

    public  void createAcoount()
    {
        String username = name.getText().toString();
        String phone = phone_num.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass))

        {

            Toast.makeText(Register_Activity.this,"All the fileds are required",Toast.LENGTH_SHORT).show();
        }
        else

        {
            progressDialog.setTitle("creating acount");
            progressDialog.setMessage("plz wait while we are checking credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            validatePhoneNumber(username , phone , pass);
        }
    }

    private void validatePhoneNumber(final String username, final String phone, final String pass) {


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object> userdatamap = new HashMap<>();
                    userdatamap.put("phone",phone);
                    userdatamap.put("password",pass);
                    userdatamap.put("username",username);
                    Rootref.child("Users").child(phone).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(Register_Activity.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent mintent = new Intent(Register_Activity.this,VerifyPhoneActivity.class);
                                mintent.putExtra("phonenumber",phone);
                                mintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mintent);
                            }
                            else
                            {
                                Toast.makeText(Register_Activity.this,"Registration failed try again",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });



                }
                else
                {
                    Toast.makeText(Register_Activity.this,"This"+phone+"Already Exist",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(Register_Activity.this,"PLEASE TRY USING ANOTHER NUMBER",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_Activity.this,MainActivity.class));


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
