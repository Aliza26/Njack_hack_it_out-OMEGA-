package com.arcreations.pdfpencil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button btnStickyNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.transparentNev);
        btnStickyNotes = findViewById(R.id.btnStickyNotes);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);




        //floating Action Button
        FloatingActionButton fab = findViewById(R.id.fabAddImg);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, RTEditorActivity.class);
                startActivity(i);


                // Toast.makeText(MainActivity.this, "Hurray", Toast.LENGTH_SHORT).show();
            }
        });


        btnStickyNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, StickyActivity.class);
                startActivity(i);

            }
        });


    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mPdf:
                Intent j = new Intent(MainActivity.this,pdfFragment.class);
                startActivity(j);
                return true;

            case R.id.mNote:
                Intent l = new Intent(MainActivity.this, notebookFragment.class);
                startActivity(l);
                return true;

        }

        return super.onOptionsItemSelected(item);}



}