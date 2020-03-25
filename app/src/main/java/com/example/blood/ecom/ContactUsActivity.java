package com.example.blood.ecom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {

    private Button email , call ,message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        email = findViewById(R.id.email_us);
        message = findViewById(R.id.message_us);
        call = findViewById(R.id.contact_us);


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEmail();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OpenCall();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               OpenMessage();
            }
        });
    }

    private void OpenMessage() {




        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("01234"));
        smsIntent.putExtra("sms_body"  , "Please Type Your Issue Here ");

        try {
            startActivity(smsIntent);

            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }


}

    private void OpenCall() {

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:1800 210 5845"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {

            startActivity(callIntent);

        }


    }


    private void OpenEmail() {

        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};


        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Type Your Complaint Here");

        try {

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            finish();
            Log.i(getString(R.string.fin), "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
