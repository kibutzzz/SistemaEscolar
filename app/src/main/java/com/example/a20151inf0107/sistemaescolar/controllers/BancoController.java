package com.example.a20151inf0107.sistemaescolar.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a20151inf0107.sistemaescolar.models.CriaBanco;

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
        valores.put(CriaBanco.NOME_PROFESSOR, nome);
        valores.put(CriaBanco.FORMACAO, formacao);

        resultado = db.insert(CriaBanco.TABELA_PROFESSORES, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public String insereRegistroMateria(int idProfessor, String materia) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_MATERIA, materia);
        valores.put(CriaBanco.FK_ID_PROFESSOR, idProfessor);

        resultado = db.insert(CriaBanco.TABELA_MATERIAS, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }


    public Cursor carregaMateria() {
        Cursor cursor;
        db = banco.getReadableDatabase();


        String SQL_QUERY = "SELECT * FROM " + banco.TABELA_MATERIAS + " m INNER JOIN "
                + banco.TABELA_PROFESSORES + " p" +
                " ON m." + banco.FK_ID_PROFESSOR + " = p." + banco.ID;

        cursor = db.rawQuery(SQL_QUERY, null);

        return cursor;
    }

    public Cursor carregaMateriaById(String id) {
        Cursor cursor;
        db = banco.getReadableDatabase();


        String SQL_QUERY = "SELECT * FROM " + banco.TABELA_MATERIAS + " m INNER JOIN "
                + banco.TABELA_PROFESSORES + " p" +
                " ON m." + banco.FK_ID_PROFESSOR + " = p." + banco.ID +
                " WHERE m."+banco.ID+" = "+ id;

        cursor = db.rawQuery(SQL_QUERY, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor carregaRegistrosProfessor() {
        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME_PROFESSOR};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_PROFESSORES,
                campos,
                null,
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }


        return cursor;
    }


    public void AlterarMateria(String codigo, String nome) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + codigo;

        valores = new ContentValues();
        valores.put(CriaBanco.NOME_MATERIA, nome);


        db.update(CriaBanco.TABELA_MATERIAS,valores,where,null);
        db.close();

    }

    public void excluirMateria(String codigo) {
        String where = CriaBanco.ID + "=" + codigo;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA_MATERIAS,where,null);
        db.close();

    }
}
