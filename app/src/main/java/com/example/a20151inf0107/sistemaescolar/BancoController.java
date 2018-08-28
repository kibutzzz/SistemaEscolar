package com.example.a20151inf0107.sistemaescolar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 20151inf0107 on 27/08/2018.
 */

public class BancoController {


    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereRegistroProfessor(String nome, String formacao) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.FORMACAO, formacao);

        resultado = db.insert(CriaBanco.TABELA_PROFESSORES, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }


    public Cursor carregaRegistrosProfessor(){
        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_PROFESSORES,
                campos,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }


}
