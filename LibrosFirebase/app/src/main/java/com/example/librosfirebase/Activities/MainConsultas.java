package com.example.librosfirebase.Activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librosfirebase.GestionLibros.LibrosFirebase;
import com.example.librosfirebase.Interfaces.InterfazLibroUI;
import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;
import com.google.firebase.database.DatabaseError;

public class MainConsultas extends AppCompatActivity {
    private int posicion;
    private TextView tvAutor, tvTitulo, tvEditorial, tvPaginas, tvTop3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posicion = getIntent().getIntExtra("posicion", -1);
        setContentView(R.layout.activity_consultas);

        initreferences();

        if (posicion == 1) {
            new LibrosFirebase().libroEditorial(new InterfazLibroUI.EscuchadorLibroAutor() {
                @Override
                public void onResponseAutor(Libro libro) {
                    tvAutor.setText(libro.getAutor());
                    tvTitulo.setText(libro.getTitulo());
                    tvEditorial.setText(libro.getEditorial());
                    tvPaginas.setText(String.valueOf(libro.getPaginas()));
                    tvTop3.setText(String.valueOf(libro.isTop3()));
                }

                @Override
                public void onErrorAutor(DatabaseError error) {
                    Toast.makeText(MainConsultas.this, "Error al mostrar los libros del auor", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            new LibrosFirebase().librosPaginas(new InterfazLibroUI.EscuchadorLibroPaginas() {
                @Override
                public void onResponse(Libro libro) {
                    tvAutor.setText(libro.getAutor());
                    tvTitulo.setText(libro.getTitulo());
                    tvEditorial.setText(libro.getEditorial());
                    tvPaginas.setText(String.valueOf(libro.getPaginas()));
                    tvTop3.setText(String.valueOf(libro.isTop3()));
                }

                @Override
                public void onError(DatabaseError error) {
                    Toast.makeText(MainConsultas.this, "Error al mostrar el libro con más páginas", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void initreferences() {
        tvAutor = findViewById(R.id.tvAutorAutor);
        tvTitulo = findViewById(R.id.tvTituloAutor);
        tvEditorial = findViewById(R.id.tvEditorialAutor);
        tvPaginas = findViewById(R.id.tvPaginasAutor);
        tvTop3 = findViewById(R.id.tvTop3Autor);
    }
}
