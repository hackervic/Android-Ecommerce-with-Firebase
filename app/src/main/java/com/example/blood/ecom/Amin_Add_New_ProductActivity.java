package com.example.blood.ecom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rey.material.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Amin_Add_New_ProductActivity extends AppCompatActivity {


    public String catagoryn;
    private android.widget.Button btnaddproduct;
    public EditText inputname, inputdescription, inputprice,inputcity,inputstate;
    private ImageView productimage;
    private String product_random_key;
    public String downloadUrl;
    private String savecurrenrdate, savecurrenttime;
    private String name, description, price,city,state;
    private static final int gallerypick = 1;
    private Uri imageuri;
    private StorageReference storageReference;
    private DatabaseReference productreference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amin__add__new__product);

        btnaddproduct = findViewById(R.id.btn_Add_new_product);
        inputname = findViewById(R.id.product_name);
        inputdescription = findViewById(R.id.product_description);
        inputprice = findViewById(R.id.product_price);
        inputcity = findViewById(R.id.product_city);
        inputstate = findViewById(R.id.product_status);
        productimage = findViewById(R.id.select_imageview);
        progressDialog  =new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference().child("Product_Images_friendly_farmer");
        catagoryn = getIntent().getExtras().get("category").toString();
        Toast.makeText(Amin_Add_New_ProductActivity.this, catagoryn, Toast.LENGTH_SHORT).show();

        productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImage();

            }
        });

        btnaddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateimage();
            }
        });

    }

    private void validateimage() {

        name = inputname.getText().toString();
        description = inputdescription.getText().toString();
        price = inputprice.getText().toString();
        city=inputcity.getText().toString();
        state=inputstate.getText().toString();

        if (imageuri == null) {
            Toast.makeText(Amin_Add_New_ProductActivity.this, "Product Image is Required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(name) || (TextUtils.isEmpty(description)) || (TextUtils.isEmpty(price))) {
            Toast.makeText(Amin_Add_New_ProductActivity.this, "Please Fill All Details Of Product", Toast.LENGTH_LONG).show();
        } else {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {

        progressDialog.setTitle("Addding New Product");
        progressDialog.setMessage("admin please wait while we are adding product");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM ,dd yyy");

        savecurrenrdate = simpleDateFormat.format(calendar.getTime());

        SimpleDateFormat simpletimeformat = new SimpleDateFormat("HH:mm:ss");
        savecurrenttime = simpletimeformat.format(calendar.getTime());

        product_random_key = savecurrenrdate + savecurrenttime;

        final StorageReference filepath = storageReference.child(imageuri.getLastPathSegment() + product_random_key + ".jpg");
        final UploadTask uploadTask = filepath.putFile(imageuri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(Amin_Add_New_ProductActivity.this, "Error" + message + "this", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(Amin_Add_New_ProductActivity.this, "image uploaded successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                      downloadUrl =   filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if (task.isSuccessful()) {
                            downloadUrl = task.getResult().toString();
                            Toast.makeText(Amin_Add_New_ProductActivity.this, "Image Sved To Database", Toast.LENGTH_SHORT).show();
                            savenewproducttodatabase();
                        }
                    }
                });
            }
        });

    }

    private void savenewproducttodatabase() {

        HashMap<String, Object> productmap = new HashMap<>();
        productmap.put("pid",product_random_key);
        productmap.put("Date", savecurrenrdate);
        productmap.put("Time", savecurrenttime);
        productmap.put("Description", description);
        productmap.put("Image",downloadUrl );
        productmap.put("Catagory", catagoryn);
        productmap.put("price", price);
        productmap.put("pname", name);
        productmap.put("pcity", city);
        productmap.put("pstatus", state);
    //    String clearCaracter = product_random_key.replace('.', ':').replace(',', ' ');
        productreference = FirebaseDatabase.getInstance().getReference().child("Services").child(city);
        productreference.updateChildren(productmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(Amin_Add_New_ProductActivity.this, "Product Added Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Amin_Add_New_ProductActivity.this,Admin_CatagoryActivity.class);
                    startActivity(intent);
                }
                else
                { String str = task.getException().toString();
                    progressDialog.dismiss();
                    Toast.makeText(Amin_Add_New_ProductActivity.this, "Error :"+str, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void PickImage() {

        Intent galleryintent = new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent, gallerypick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == gallerypick && resultCode == RESULT_OK && data != null) {
            imageuri = data.getData();
            productimage.setImageURI(imageuri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
