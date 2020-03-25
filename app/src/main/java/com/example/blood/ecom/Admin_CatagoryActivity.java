package com.example.blood.ecom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;


public class Admin_CatagoryActivity extends AppCompatActivity {

//    private ImageView tShirts, sportsTShirts, femaleDresses, sweathers;
//    private ImageView glasses, hatsCaps, walletsBagsPurses, shoes;
//    private ImageView headPhonesHandFree, Laptops, watches, mobilePhones;
   private LinearLayout trac,machine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__catagory);

//        tShirts = findViewById(R.id.t_shirt);
//        sportsTShirts = findViewById(R.id.sports_shirt);
//        femaleDresses = findViewById(R.id.womens_dress);
//        sweathers = findViewById(R.id.sweaters);
//        glasses = findViewById(R.id.glasses);
//        hatsCaps = findViewById(R.id.hats);
//        walletsBagsPurses = findViewById(R.id.purse_bags);
//        shoes = findViewById(R.id.shoess);
//        headPhonesHandFree = findViewById(R.id.headphones);
//        Laptops = findViewById(R.id.laptops);
//        watches = findViewById(R.id.watches);
//        mobilePhones = findViewById(R.id.mobiles);

        trac=(LinearLayout) findViewById(R.id.ltractor);
        machine=(LinearLayout) findViewById(R.id.lmachine);

        trac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
              intent.putExtra("category", "tractor");
              startActivity(intent);
            }
        });
        machine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
                intent.putExtra("category", "machinery");
                startActivity(intent);
            }
        });


//        tShirts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "tShirts");
//                startActivity(intent);
//            }
//        });
//
//
//        sportsTShirts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Jewellery");
//                startActivity(intent);
//            }
//        });
//
//
//        femaleDresses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Female Dresses");
//                startActivity(intent);
//            }
//        });
//
//
//        sweathers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Sweathers");
//                startActivity(intent);
//            }
//        });
//
//
//        glasses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Glasses");
//                startActivity(intent);
//            }
//        });
//
//
//        hatsCaps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Hats Caps");
//                startActivity(intent);
//            }
//        });
//
//
//
//        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Wallets Bags Purses");
//                startActivity(intent);
//            }
//        });
//
//
//        shoes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Shoes");
//                startActivity(intent);
//            }
//        });
//
//
//
//        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "HeadPhones HandFree");
//                startActivity(intent);
//            }
//        });
//
//
//        Laptops.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Laptops");
//                startActivity(intent);
//            }
//        });
//
//
//        watches.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Watches");
//                startActivity(intent);
//            }
//        });
//
//
//        mobilePhones.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(Admin_CatagoryActivity.this, Amin_Add_New_ProductActivity.class);
//                intent.putExtra("category", "Mobile Phones");
//                startActivity(intent);
//            }
//        });


    }
}
