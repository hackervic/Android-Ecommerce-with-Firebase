package com.example.blood.ecom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.blood.ecom.Model.Products;
import com.example.blood.ecom.Model.Services;
import com.example.blood.ecom.Prevalent.prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

  //  private FloatingActionButton floatingActionButton;
    private ElegantNumberButton elegantNumberButton;
    private ImageView imageView;
    private TextView dname , ddescription , dprice;
    private Button button;
   public String  productid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        productid =getIntent().getStringExtra("pid");
        elegantNumberButton = findViewById(R.id.elegent_button);
        imageView = findViewById(R.id.detailed_product_image);
        dname = findViewById(R.id.detail_product_name);
        ddescription = findViewById(R.id.detail_product_description);
        dprice = findViewById(R.id.detail_product_price);
        button = findViewById(R.id.fab_pr_de);


            GetProductDetail(productid);
           button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addProductToCartList();
                }
            });

    }

    private void addProductToCartList() {

        String savecurrenttime , savecurrentdate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrentdate = currentdate.format(calendar.getTime());

        SimpleDateFormat currenttime= new SimpleDateFormat("HH:mm:ss a");
        savecurrenttime = currentdate.format(calendar.getTime());

        Amin_Add_New_ProductActivity admin = new Amin_Add_New_ProductActivity();


     final  DatabaseReference currentlist = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String , Object> cartmap =new HashMap<>();
        cartmap.put("pid",productid);
        cartmap.put("pname",dname.getText().toString());
        cartmap.put("quantity",elegantNumberButton.getNumber());
        cartmap.put("pprice",dprice.getText().toString());
        cartmap.put("date",savecurrentdate);
        cartmap.put("time",savecurrenttime);
        cartmap.put("discount","");

        currentlist.child("User view").child(prevalent.currentOnlineUser.getPhone()).child("Products")
                .child(productid).updateChildren(cartmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                        currentlist.child("Admin view").child(prevalent.currentOnlineUser.getPhone()).child("Products")
                                .child(productid).updateChildren(cartmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(ProductDetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(ProductDetailActivity.this,HomeActivity.class);
                                    startActivity(i);
                                }

                            }
                        });

                    }

                    }
                });



    }

    private void GetProductDetail(String productid) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Services");
        databaseReference.child(productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    Services services = dataSnapshot.getValue(Services.class);
                    dname.setText(services.getPname());
                    ddescription.setText(services.getDescription());
                    dprice.setText(services.getPrice());
                    Picasso.get().load(services.getImage()).into(imageView);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
