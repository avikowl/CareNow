package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InformationActivity.this, HomeActivity.class));
            }
        });
        CardView tuberculosis = findViewById(R.id.cardTuberculosis);
        tuberculosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(InformationActivity.this, DiseaseDetailsActivity.class);
                it.putExtra("title", "Tuberculosis");
                startActivity(it);
            }
        });
        CardView pneumonia = findViewById(R.id.cardPneumonia);
        pneumonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(InformationActivity.this, DiseaseDetailsActivity.class);
                it.putExtra("title", "Pneumonia");
                startActivity(it);
            }
        });
        CardView malaria = findViewById(R.id.cardMalaria);
        malaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(InformationActivity.this, DiseaseDetailsActivity.class);
                it.putExtra("title", "Malaria");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardDengue);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(InformationActivity.this, DiseaseDetailsActivity.class);
                it.putExtra("title", "Dengue");
                startActivity(it);
            }
        });
        CardView cardiologist = findViewById(R.id.cardAnemia);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(InformationActivity.this, DiseaseDetailsActivity.class);
                it.putExtra("title", "Anemia");
                startActivity(it);
            }
        });
    }
}