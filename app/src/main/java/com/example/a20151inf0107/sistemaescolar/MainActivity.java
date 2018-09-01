package com.example.a20151inf0107.sistemaescolar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaRegistrosProfessor();

        String[] nomeCampos = {CriaBanco.ID, CriaBanco.NOME};
        int[] idViews = {R.id.view_id, R.id.view_nome};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_item_materia, cursor, nomeCampos, idViews, 0);

        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastrar_professor:
                Intent intentInsertProfessor = new Intent(getBaseContext(), InsertProfessorActivity.class);
                startActivity(intentInsertProfessor);
                break;
//            case R.id.alterar:
//                Intent intentAlter = new Intent(getBaseContext(), AlterDataActivity.class);
//                startActivity(intentAlter);
//                break;
            case R.id.cadastrar_materia:
                Intent intentInsertMateria = new Intent(getBaseContext(), InsertMateriaActivity.class);
                startActivity(intentInsertMateria);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
