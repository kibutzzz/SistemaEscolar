package com.example.a20151inf0107.sistemaescolar.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 20151inf0107 on 27/08/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {

    public static final String TABELA_MATERIAS = "materias";
    public static final String TABELA_PROFESSORES = "professores";
    public static final String ID = "_id";
    public static final String NOME_PROFESSOR = "nome_professor";
    public static final String NOME_MATERIA = "nome_materia";
    public static final String FK_ID_PROFESSOR = "id_professor";
    public static final String FORMACAO = "formacao";
    private static final String NOME_BANCO = "bancoSistemaEscolar.db";
    private static final int VERSAO = 3;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_PROFESSORES + "( "
                + ID + " integer primary key autoincrement, "
                + NOME_PROFESSOR + " text, "
                + FORMACAO + " text"
                + "); ";


        db.execSQL(sql);
        sql = "CREATE TABLE " + TABELA_MATERIAS + "( "
                + ID + " integer primary key autoincrement, "
                + NOME_MATERIA + " text, "
                + FK_ID_PROFESSOR + " integer, "
                + "FOREIGN KEY( " + FK_ID_PROFESSOR + " ) REFERENCES " + TABELA_PROFESSORES + "(" + ID + ")"
                + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PROFESSORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_MATERIAS);
        onCreate(db);
    }
}
