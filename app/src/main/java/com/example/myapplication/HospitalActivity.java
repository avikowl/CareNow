package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        String[] contacts = {
                "Ambulance: 911",
                "Poison Control: 1-800-222-1222",
                "Suicide Prevention Lifeline: 988",
                "Mental Health Crisis: 1-800-273-TALK",
                "Domestic Violence Helpline: 1-800-799-7233",
                "Substance Abuse Helpline: 1-800-662-HELP",
                "Child Abuse Hotline: 1-800-422-4453",
                "Veterans Crisis Line: 988 (then press 1)",
                "Disaster Distress Helpline: 1-800-985-5990",
                "National Sexual Assault Hotline: 1-800-656-4673"
        };

        String[] phoneNumbers = {
                "911",                 // Ambulance
                "1-800-222-1222",      // Poison Control
                "988",                 // Suicide Prevention Lifeline
                "1-800-273-8255",      // Mental Health Crisis
                "1-800-799-7233",      // Domestic Violence Helpline
                "1-800-662-4357",      // Substance Abuse Helpline
                "1-800-422-4453",      // Child Abuse Hotline
                "988",                 // Veterans Crisis Line
                "1-800-985-5990",      // Disaster Distress Helpline
                "1-800-656-4673"       // National Sexual Assault Hotline
        };

        LinearLayout emergencyLayout = findViewById(R.id.emergencyLayout);

        for (int i = 0; i < contacts.length; i++) {
            TextView contactTextView = new TextView(this);
            contactTextView.setText(contacts[i]);
            contactTextView.setTextSize(18);
            contactTextView.setPadding(16, 16, 16, 16);
            emergencyLayout.addView(contactTextView);

            Button callButton = new Button(this);
            callButton.setText("Call " + contacts[i].split(":")[0]);
            final String phoneNumber = phoneNumbers[i]; // Use final variable for intent
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);
                }
            });

            emergencyLayout.addView(callButton);
        }
    }
}
