package com.example.a20151inf0107.sistemaescolar.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.a20151inf0107.sistemaescolar.R;
import com.example.a20151inf0107.sistemaescolar.controllers.BancoController;
import com.example.a20151inf0107.sistemaescolar.models.CriaBanco;
import com.example.a20151inf0107.sistemaescolar.views.InsertMateriaActivity;
import com.example.a20151inf0107.sistemaescolar.views.InsertProfessorActivity;

public class MainActivity extends AppCompatActivity {
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = findViewById(R.id.listView);

        BancoController crud = new BancoController(getBaseContext());
        cursor = crud.carregaMateria();

        String[] nomeCampos = new String[]{ CriaBanco.NOME_MATERIA, CriaBanco.NOME_PROFESSOR};
        int[] idViews = {R.id.materia, R.id.professor};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_item_materia, cursor, nomeCampos, idViews, 0);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo;
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));

                Intent manageActivityIntent = new Intent(MainActivity.this, ManageActivity.class);
                manageActivityIntent.putExtra("codigo", codigo);
                startActivity(manageActivityIntent);
                finish();
            }
        });
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

            case R.id.cadastrar_materia:
                Intent intentInsertMateria = new Intent(getBaseContext(), InsertMateriaActivity.class);
                startActivity(intentInsertMateria);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
