package com.example.a20151inf0107.sistemaescolar.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.a20151inf0107.sistemaescolar.R;
import com.example.a20151inf0107.sistemaescolar.controllers.BancoController;

public class InsertProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_professor);


        AppCompatButton ButtonCadastrar = findViewById(R.id.button_cadastrar);


        ButtonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());
                AppCompatEditText EditTextNome = findViewById(R.id.edit_text_nome);
                AppCompatEditText EditTextFormacao = findViewById(R.id.edit_text_formacao);

                String nome = EditTextNome.getText().toString();
                String formacao = EditTextFormacao.getText().toString();
                String resultado;

                resultado = crud.insereRegistroProfessor(nome, formacao);
                Toast.makeText(InsertProfessorActivity.this, resultado, Toast.LENGTH_LONG).show();
                finish();

            }
        });

    }
}
