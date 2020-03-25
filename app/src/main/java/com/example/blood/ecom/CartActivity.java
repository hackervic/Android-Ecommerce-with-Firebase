package com.example.blood.ecom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.ecom.Model.Cart;
import com.example.blood.ecom.Model.Products;
import com.example.blood.ecom.Model.Users;
import com.example.blood.ecom.Prevalent.prevalent;
import com.example.blood.ecom.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.String.valueOf;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button next_btn;
    private TextView total_price ,message;
    public int overtotalprice = 0 ;
    public DatabaseReference clist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycle1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        message =(TextView)findViewById(R.id.msg1);
        next_btn = findViewById(R.id.next_process_btn);
        total_price = findViewById(R.id.tprice);


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this ,confirm_final_orderActivity.class);
                total_price.setText(getString(R.string.tp)+valueOf(overtotalprice)+"$");

                intent.putExtra("Total Price ",valueOf(overtotalprice));
                    startActivity(intent);
                    finish();
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();
        //CheckOrderReference();

   clist = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(clist.child("User view").child(prevalent.currentOnlineUser.getPhone()).child("Products"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {

                holder.txtpname.setText(model.getPname());
                holder.txtpquantity.setText(getString(R.string.qn) + model.getQuantity());
                holder.txtpprice.setText(getString(R.string.ff) + model.getPprice() +  " $");

                int oneTypeProductPrice =((Integer.valueOf(model.getPprice()))) * Integer.valueOf(model.getQuantity());
                overtotalprice = overtotalprice + oneTypeProductPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence option[] = new CharSequence[]
                                        {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Option");

                        builder.setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);

                                }
                                if (which == 1) {
                                    clist.child("User view").child(prevalent.currentOnlineUser.getPhone()).child("Products")
                                            .child(model.getPid()).setValue(null)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(CartActivity.this, "Item removed from cart", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                    } else {
                                                        Toast.makeText(CartActivity.this, "Item removed failed", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });

                                }
                            }
                        });
                        builder.show();
                    }
                });


            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }


//   protected void CheckOrderReference()
//    {
//        clist = FirebaseDatabase.getInstance().getReference().child("Orders")
//            .child(prevalent.currentOnlineUser.getPhone());
//        clist.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
             //   String shppindstate = dataSnapshot.child("state").getValue().toString();
//                String usrName = dataSnapshot.child("name").getValue().toString();
//
//                if(shppindstate.equals("shipped"))
//                {
//                    total_price.setText("dear"+usrName+"\n order is shipped succesfully");
//                    recyclerView.setVisibility(View.INVISIBLE);
//                    message.setVisibility(View.VISIBLE);
//                    next_btn.setVisibility(View.INVISIBLE);
//                    next_btn.setEnabled(false);
//                    Toast.makeText(CartActivity.this, "you can purchase more products once you recieved your final order", Toast.LENGTH_SHORT).show();
//                }
//                else if(shppindstate.equals("not_shipped"))
//                {
//                    message.setText(R.string.sh);
//                    message.setText(R.string.adf);
//                    recyclerView.setVisibility(View.INVISIBLE);
//                    message.setVisibility(View.VISIBLE);
//                    next_btn.setVisibility(View.INVISIBLE);
//                    next_btn.setEnabled(false);
//                    Toast.makeText(CartActivity.this, "you can purchase more products once you recieved your final order", Toast.LENGTH_SHORT).show();
//
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }


