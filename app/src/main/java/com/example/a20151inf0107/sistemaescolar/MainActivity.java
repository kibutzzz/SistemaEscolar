package com.example.a20151inf0107.sistemaescolar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.cadastrar:
                Intent intentInsert = new Intent(getBaseContext(), InsertProfessorActivity.class);
                startActivity(intentInsert);
                break;
//            case R.id.alterar:
//                Intent intentAlter = new Intent(getBaseContext(), AlterDataActivity.class);
//                startActivity(intentAlter);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
