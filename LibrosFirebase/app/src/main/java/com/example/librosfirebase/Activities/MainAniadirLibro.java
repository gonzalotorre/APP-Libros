package com.example.librosfirebase.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librosfirebase.GestionLibros.LibrosFirebase;
import com.example.librosfirebase.Interfaces.InterfazLibroUI;
import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;

public class MainAniadirLibro extends AppCompatActivity {

    private EditText etAutor, etTitulo, etEditorial, etPaginas, etTop3;
    private Button btAniadir;
    private InterfazLibroUI libros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aniadir_libro);
        initreferences();
        libros = new LibrosFirebase();

        btAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro libro = new Libro(
                        etAutor.getText().toString(),
                        etTitulo.getText().toString(),
                        etEditorial.getText().toString(),
                        Boolean.getBoolean(etTop3.getText().toString()),
                        Integer.parseInt(etPaginas.getText().toString()),
                        null, null);
                libros.aniadirLibro(libro);
                finish();
            }
        });
    }

    public void initreferences(){
        etAutor = findViewById(R.id.etAutor);
        etTitulo = findViewById(R.id.etTitulo);
        etEditorial = findViewById(R.id.etEditorial);
        etPaginas = findViewById(R.id.etPaginas);
        etTop3 = findViewById(R.id.etTop3);
        btAniadir = findViewById(R.id.btAniadir);
    }

}
