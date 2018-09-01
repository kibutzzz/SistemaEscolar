package com.example.a20151inf0107.sistemaescolar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class InsertMateriaActivity extends AppCompatActivity {

    private static final String TAG = "LOG TAG ---";
    AppCompatEditText editTextMateria;
    AppCompatSpinner spinnerProfessor;
    AppCompatButton buttonCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_materia);


        editTextMateria = findViewById(R.id.materia);
        spinnerProfessor = findViewById(R.id.professor);
        buttonCadastrar = findViewById(R.id.button_cadastrar);


        setSpinnerValues();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());

                int id = (int) spinnerProfessor.getSelectedItemId();
                String nome = editTextMateria.getText().toString();

                String message = crud.insereRegistroMateria(id, nome);

                Log.v(TAG, message);
                finish();
            }
        });
    }

    private void setSpinnerValues() {
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaRegistrosProfessor();

        String[] columns = {
                CriaBanco.NOME,
                CriaBanco.ID
        };

        int[] views = {
                android.R.id.text1
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                cursor,
                columns,
                views,
                0);
        cursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProfessor.setAdapter(cursorAdapter);
    }
}
