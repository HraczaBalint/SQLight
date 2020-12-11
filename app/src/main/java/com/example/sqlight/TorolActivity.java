package com.example.sqlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TorolActivity extends AppCompatActivity {

    private EditText edId;
    private Button btnTorol, btnVissza;

    private DBhelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torol);

        init();

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(TorolActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btnTorol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edId.getText().toString().trim();
                if (id.isEmpty()) {
                    Toast.makeText(TorolActivity.this, "ID kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!adatbazis.idLetezik(id)) {
                    Toast.makeText(TorolActivity.this, "Ilyen ID nem létezik!", Toast.LENGTH_SHORT).show();
                }
                if (adatbazis.adatTorles(id)) {
                    Toast.makeText(TorolActivity.this, "Sikeres adat törlés!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TorolActivity.this, "Sikertelen adat törlés!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {

        edId = findViewById(R.id.et_id_torol);
        btnTorol = findViewById(R.id.btn_torol);
        btnVissza = findViewById(R.id.btn_torol_visza);

        adatbazis = new DBhelper(TorolActivity.this);
    }
}