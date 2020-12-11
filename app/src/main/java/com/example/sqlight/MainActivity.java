package com.example.sqlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnOlvas, btnRogzit, btnTorol, btnModosit;
    private TextView textAdatok;

    private  DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intit();
        linsters();
    }

    private  void adatLekerdezes() {
        Cursor adatok = adatbazis.adatLekérdezés();
        if (adatok == null) {
            Toast.makeText(this, "Hiba történt az adatok lekérdezése során!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount() == 0) {
            Toast.makeText(this, "Még nincs felvéve adat!", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder builder = new StringBuilder();
        while (adatok.moveToNext()) {
            builder.append("ID: ").append(adatok.getInt(0)).append("\n");
            builder.append("Név: ").append(adatok.getString(1)).append("\n");
            builder.append("E-mail: ").append(adatok.getString(2)).append("\n");
            builder.append("Jegy: ").append(adatok.getInt(3)).append("\n\n");
        }
        textAdatok.setText(builder.toString());
        Toast.makeText(this, "Sikeres adat lekérdezés!", Toast.LENGTH_SHORT).show();
    }

    private void linsters() {
        btnOlvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatLekerdezes();
            }
        });

        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rogzitesre = new Intent(MainActivity.this, RogzitActivity.class);
                startActivity(rogzitesre);
                finish();
            }
        });
        btnTorol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent torlesre = new Intent(MainActivity.this, TorolActivity.class);
                startActivity(torlesre);
                finish();
            }
        });
        btnModosit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modositasra = new Intent(MainActivity.this, ModositActivity.class);
                startActivity(modositasra);
                finish();
            }
        });

    }

    private void intit() {
        btnOlvas = findViewById(R.id.btn_olvas);
        btnRogzit = findViewById(R.id.btn_rogzit);
        btnTorol = findViewById(R.id.btn_torol);
        btnModosit = findViewById(R.id.btn_modosit);

        textAdatok = findViewById(R.id.text_adatok);
        textAdatok.setMovementMethod(new ScrollingMovementMethod());

        adatbazis = new DBhelper(MainActivity.this);
    }
}