package com.arcreations.pdfpencil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class notebookFragment extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<RegistrationModal> registrationModalArrayList;
    private DBHandler dbHandler;
    private RegistrationRVAdapter registrationRVAdapter;
    private RecyclerView registrationRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_fragment);

        // initializing our all variables.
        registrationModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(notebookFragment.this);

        // getting our course array
        // list from db handler class.
        registrationModalArrayList = dbHandler.readRegistration();



        // on below line passing our array lost to our adapter class.
        registrationRVAdapter = new RegistrationRVAdapter(registrationModalArrayList, notebookFragment.this);
        registrationRV = findViewById(R.id.idRVRegistration);




        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(notebookFragment.this, RecyclerView.VERTICAL, false);
        registrationRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        registrationRV.setAdapter(registrationRVAdapter);


    }




    public void newActivity(){
        Intent i = new Intent(notebookFragment.this, MainActivity.class);
        startActivity(i);}

    public void saveActivity(){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            intent = new Intent(Intent.ACTION_CREATE_DOCUMENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        }
        intent.setType("*/*");
        startActivity(intent);}






}