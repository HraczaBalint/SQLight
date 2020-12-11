package com.example.sqlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModositActivity extends AppCompatActivity {

    private EditText etNev, etEmail, etJegy, etId;
    private Button btnModosit, btnVissza;
    DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modosit);

        init();

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(ModositActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btnModosit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatModositas();
            }
        });
    }

    private void adatModositas() {
        String id = etId.getText().toString().trim();
        String nev = etNev.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String jegy = etJegy.getText().toString().trim();
        if (id.isEmpty()) {
            Toast.makeText(this, "ID kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!adatbazis.idLetezik(id)) {
            Toast.makeText(this, "Ilyen ID-val nem létezik rekord", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nev.isEmpty()) {
            Toast.makeText(this, "Név kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "E-mail kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jegy.isEmpty()) {
            Toast.makeText(this, "Jegy kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        int jegySzam = Integer.parseInt(jegy);
        if (jegySzam < 1 || jegySzam > 5) {
            Toast.makeText(this, "A jegy 1 és 5 között szám lehet!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatbazis.adatModositas(id, nev, email, jegy)) {
            Toast.makeText(this, "Sikeres adat módosítás!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sikertelen adat módosítás!", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        etNev = findViewById(R.id.et_nev_modosit);
        etEmail = findViewById(R.id.et_email_modosit);
        etJegy = findViewById(R.id.et_jegy_modosit);
        etId = findViewById(R.id.et_id_modosit);

        btnModosit = findViewById(R.id.btn_modosit);
        btnVissza = findViewById(R.id.btn_modosit_vissza);

        adatbazis = new DBhelper(ModositActivity.this);
    }

}