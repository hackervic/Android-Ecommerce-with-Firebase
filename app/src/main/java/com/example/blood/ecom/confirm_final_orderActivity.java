package com.example.blood.ecom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.ecom.Prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class confirm_final_orderActivity extends AppCompatActivity {

    private EditText sname, saddress, spcode, snumber, scity;
    private TextView tprice;
    private Button confirm;
    private String totalAmount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalAmount = getIntent().getStringExtra("Total Price ");
        //   Toast.makeText(this, totalAmount, Toast.LENGTH_SHORT).show();
        tprice = findViewById(R.id.cart_value);
        sname = findViewById(R.id.Shipment_name);
        saddress = findViewById(R.id.Shipment_address);
        scity = findViewById(R.id.Shipment_city);
        snumber = findViewById(R.id.Shipment_no);
        spcode = findViewById(R.id.Shipment_pincode);
        confirm = findViewById(R.id.btn_confirm);
        tprice.setText(getString(R.string.cv) + String.valueOf(totalAmount) + getString(R.string.c));


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, addres, city, number, code;
                name = sname.getText().toString();
                addres = saddress.getText().toString();
                city = scity.getText().toString();
                number = snumber.getText().toString();
                code = spcode.getText().toString();


                if (TextUtils.isEmpty(name) || (TextUtils.isEmpty(addres)) || (TextUtils.isEmpty(code)) || (TextUtils.isEmpty(city)) || TextUtils.isEmpty(number)) {
                    Toast.makeText(confirm_final_orderActivity.this, "All Fields Are Cumpulsory", Toast.LENGTH_SHORT).show();
                } else {
                    ConfirmOrder();
                }
            }
        });

    }

    private void ConfirmOrder() {
        final String savecurrenttime, savecurrentdate;

        final Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrentdate = currentdate.format(calendar.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currentdate.format(calendar.getTime());

        final DatabaseReference orderreference = getInstance().getReference()
                .child("Orders")
                .child(prevalent.currentOnlineUser.getPhone());

        HashMap<String, Object> ordermap = new HashMap<>();
        ordermap.put("total amount", totalAmount);
        ordermap.put("name", sname.getText().toString());
        ordermap.put("address", saddress.getText().toString());
        ordermap.put("city", scity.getText().toString());
        ordermap.put("pincode", spcode.getText().toString());
        ordermap.put("phone number", snumber.getText().toString());
        ordermap.put("date", savecurrentdate);
        ordermap.put("time", savecurrenttime);
        ordermap.put("state", "not shiped");

        orderreference.updateChildren(ordermap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User view")
                            .child(prevalent.currentOnlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(confirm_final_orderActivity.this, "Your Order Confirmed ", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(confirm_final_orderActivity.this, HomeActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        Toast.makeText(confirm_final_orderActivity.this, "Thanks For Shopping", Toast.LENGTH_SHORT).show();
                                        startActivity(i);
                                    }
                                }
                            });
                } else {
                    Toast.makeText(confirm_final_orderActivity.this, "Order Confirmation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
