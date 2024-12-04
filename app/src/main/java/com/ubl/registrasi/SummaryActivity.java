package com.ubl.registrasi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    private TextView tvFakultas, tvNIM, tvNama, tvEmail, tvPhone, tvJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvFakultas = findViewById(R.id.tvFakultas);
        tvNIM = findViewById(R.id.tvNIM);
        tvNama = findViewById(R.id.tvNama);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvJurusan = findViewById(R.id.tvJurusan);

        // Get data from the intent
        Intent intent = getIntent();
        String fakultas = intent.getStringExtra("fakultas");
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String jurusan = intent.getStringExtra("jurusan");

        // Set the values to the TextViews
        tvFakultas.setText(fakultas);
        tvNIM.setText(nim);
        tvNama.setText(nama);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvJurusan.setText(jurusan);

        // Set up click listeners for email and phone
        tvEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        });

        tvPhone.setOnClickListener(v -> {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            startActivity(phoneIntent);
        });
    }
}
