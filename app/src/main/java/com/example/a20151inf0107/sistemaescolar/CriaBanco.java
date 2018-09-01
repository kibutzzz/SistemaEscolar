package com.example.a20151inf0107.sistemaescolar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 20151inf0107 on 27/08/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {

    static final String TABELA_MATERIAS = "materias";
    static final String TABELA_PROFESSORES = "professores";
    static final String ID = "_id";
    static final String NOME = "nome";
    static final String FK_ID_PROFESSOR = "id_professor";
    static final String FORMACAO = "formacao";
    private static final String NOME_BANCO = "bancoSistemaEscolar.db";
    private static final int VERSAO = 2;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_PROFESSORES + "( "
                + ID + " integer primary key autoincrement, "
                + NOME + " text, "
                + FORMACAO + " text"
                + "); ";


        db.execSQL(sql);
        sql = "CREATE TABLE " + TABELA_MATERIAS + "( "
                + ID + " integer primary key autoincrement, "
                + NOME + " text, "
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
