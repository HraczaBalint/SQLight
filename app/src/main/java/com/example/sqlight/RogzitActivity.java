package com.example.sqlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RogzitActivity extends AppCompatActivity {

    private Button btnRogzit, btnVissza;
    private EditText etNev, etEmail, etJegy;

    private DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzit);

        init();

        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissz = new Intent(RogzitActivity.this, MainActivity.class);
                startActivity(vissz);
                finish();
            }
        });
    }

    private void adatRogzites() {
        String nev = etNev.getText().toString().trim().trim().trim();
        String email = etEmail.getText().toString().trim().trim().trim();
        String jegy = etJegy.getText().toString().trim().trim().trim();
        
        if (nev.isEmpty()) {
            Toast.makeText(this, "Név megaádsa kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "E-mail megaádsa kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jegy.isEmpty()) {
            Toast.makeText(this, "Jegy megaádsa kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        int jegySzam = Integer.parseInt(jegy);
        if (jegySzam < 1 || jegySzam > 5)
        {
            Toast.makeText(this, "Jegy 1 és 5 közötti szám lehet!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (adatbazis.adatRogzites(nev, email, jegy)) {
            Toast.makeText(this, "Sikeres adatrögzítés!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sikertelen adatrögzítés!", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {

        btnRogzit = findViewById(R.id.btn_rogzit);
        btnVissza = findViewById(R.id.btn_rogzit_vissza);

        etNev = findViewById(R.id.et_nev);
        etEmail = findViewById(R.id.et_email);
        etJegy = findViewById(R.id.et_jegy);

        adatbazis = new DBhelper(RogzitActivity.this);
    }
}