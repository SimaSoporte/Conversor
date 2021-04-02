package com.poi.conversor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etDolares, etEuros, etCambio;
    private RadioButton rbDolaresEuros, rbEurosDolares;
    private Button btConvertir;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        inicializarVista();

        vm.getActivoEtDolar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etDolares.setEnabled(aBoolean);
                limpiarCampos();
                etDolares.requestFocus();
            }
        });
        vm.getActivoEtEuro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etEuros.setEnabled(aBoolean);
                limpiarCampos();
                etEuros.requestFocus();
            }
        });
        vm.getResultadoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etCambio.setText(s);
            }
        });

    }

    public void cambiarRadioButton(View v) {
        vm.cambiarEstadoET(v.getId(), rbDolaresEuros.getId(), rbEurosDolares.getId());
    }
    public void convertirMoneda(View v) {
        vm.calcular(rbDolaresEuros.isChecked(), etDolares.getText().toString(), etEuros.getText().toString());
    }

    private void inicializarVista() {
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);
        etCambio = findViewById(R.id.etCambio);
        rbDolaresEuros = findViewById(R.id.rbDolaresEuros);
        rbEurosDolares = findViewById(R.id.rbEurosDolares);
        btConvertir = findViewById(R.id.btConvertir);

        etDolares.requestFocus();

    }

    private void limpiarCampos() {
        etDolares.setText("");
        etEuros.setText("");
        etCambio.setText("");
    }
}