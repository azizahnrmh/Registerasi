package com.ubl.registrasi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class RegistrasiActivity extends AppCompatActivity {

    private EditText etNIM, etNama, etEmail, etPhone, etJurusan;
    private Spinner spinnerFakultas;
    private MaterialButton btnRegister, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        Spinner fakultasSpinner = findViewById(R.id.spinnerFakultas);

        // Get the string array from resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fakultas_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        fakultasSpinner.setAdapter(adapter);

        etNIM = findViewById(R.id.etNIM);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etJurusan = findViewById(R.id.etJurusan);
        spinnerFakultas = findViewById(R.id.spinnerFakultas);
        btnRegister = findViewById(R.id.btnRegister);
        btnReset = findViewById(R.id.btnReset);

        btnRegister.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(RegistrasiActivity.this, SummaryActivity.class);
                intent.putExtra("fakultas", spinnerFakultas.getSelectedItem().toString());
                intent.putExtra("nim", etNIM.getText().toString());
                intent.putExtra("nama", etNama.getText().toString());
                intent.putExtra("email", etEmail.getText().toString());
                intent.putExtra("phone", etPhone.getText().toString());
                intent.putExtra("jurusan", etJurusan.getText().toString());
                startActivity(intent);
            }
        });

        btnReset.setOnClickListener(v -> {
            etNIM.setText("");
            etNama.setText("");
            etEmail.setText("");
            etPhone.setText("");
            etJurusan.setText("");
            spinnerFakultas.setSelection(0);
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(etNIM.getText())) {
            etNIM.setError("NIM is required");
            return false;
        }

        if (TextUtils.isEmpty(etNama.getText())) {
            etNama.setError("Nama Lengkap is required");
            return false;
        }

        if (TextUtils.isEmpty(etEmail.getText()) || !Patterns.EMAIL_ADDRESS.matcher(etEmail.getText()).matches()) {
            etEmail.setError("Valid email is required");
            return false;
        }

        if (TextUtils.isEmpty(etPhone.getText()) || !TextUtils.isDigitsOnly(etPhone.getText())) {
            etPhone.setError("Valid phone number is required");
            return false;
        }

        if (TextUtils.isEmpty(etJurusan.getText())) {
            etJurusan.setError("Jurusan is required");
            return false;
        }

        return true;
    }
}
