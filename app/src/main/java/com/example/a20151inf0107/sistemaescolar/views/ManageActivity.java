package com.example.a20151inf0107.sistemaescolar.views;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.a20151inf0107.sistemaescolar.R;
import com.example.a20151inf0107.sistemaescolar.controllers.BancoController;
import com.example.a20151inf0107.sistemaescolar.models.CriaBanco;

public class ManageActivity extends AppCompatActivity {
    private AppCompatTextView nomeProfessor;
    private AppCompatTextView formacaoProfessor;
    private AppCompatEditText nomeMateria;
    private AppCompatButton buttonAlterar;
    private AppCompatButton buttonExcluir;

    private Cursor cursor;
    private String codigo;
    private BancoController crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        nomeProfessor = findViewById(R.id.nome_professor);
        formacaoProfessor = findViewById(R.id.formacao_professor);
        nomeMateria = findViewById(R.id.nome_materia);

        buttonAlterar = findViewById(R.id.button_alterar);
        buttonExcluir = findViewById(R.id.button_excluir);

        cursor = crud.carregaMateriaById(codigo);

        nomeMateria.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_MATERIA)));
        formacaoProfessor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.FORMACAO)));
        nomeProfessor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_PROFESSOR)));

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.AlterarMateria(codigo, nomeMateria.getText().toString());
                Intent intent = new Intent(ManageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.excluirMateria(codigo);
                Intent intent = new Intent(ManageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
