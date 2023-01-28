package com.arcreations.pdfpencil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class pdfFragment extends AppCompatActivity {

    PDFView pdfView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_fragment);


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


        pdfView = findViewById(R.id.pdfView);



        Intent intent ;
            intent = new Intent(Intent.ACTION_MEDIA_SHARED, Uri.fromFile(Environment.getExternalStorageDirectory()));

        intent.setType("application/pdf");
        this.startActivity(intent);

    }


}