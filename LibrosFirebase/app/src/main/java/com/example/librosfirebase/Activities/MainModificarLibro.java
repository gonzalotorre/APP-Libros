package com.example.librosfirebase.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librosfirebase.GestionLibros.LibrosFirebase;
import com.example.librosfirebase.Interfaces.InterfazLibroUI;
import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;

public class MainModificarLibro extends AppCompatActivity {

    private EditText etAutor, etTitulo, etEditorial, etPaginas, etTop3;
    private Button btModificar;
    private InterfazLibroUI libros;
    private Libro libroModificado;
    private int posicion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_libro);

        initreferences();
        mostrarDatosLibro();
        libros = new LibrosFirebase();

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent();
                String autor = etAutor.getText().toString();
                volver.putExtra("autorModificado", autor);
                String titulo = etTitulo.getText().toString();
                volver.putExtra("tituloModificado", titulo);
                String editorial = etEditorial.getText().toString();
                volver.putExtra("editorialModificado", editorial);
                int paginas = Integer.parseInt(etPaginas.getText().toString());
                volver.putExtra("paginasModificado", paginas);
                boolean top3 = Boolean.valueOf(etTop3.getText().toString());
                volver.putExtra("top3Modificado", top3);
                setResult(RESULT_OK, volver);

                libroModificado = new Libro(autor, titulo, editorial, top3, paginas, null, null);
                String id = MainActivity.getAdaptadorLibros().getKey(posicion);
                libros.modificarLibro(libroModificado, id);
                finish();
            }
        });
    }

    public void mostrarDatosLibro(){
        etAutor.setText(getIntent().getStringExtra("autor"));
        etTitulo.setText(getIntent().getStringExtra("titulo"));
        etEditorial.setText(getIntent().getStringExtra("editorial"));
        int paginas = getIntent().getIntExtra("paginas", -1);
        etPaginas.setText(String.valueOf(paginas));
        boolean top3 = getIntent().getBooleanExtra("top3", true);
        etTop3.setText(String.valueOf(top3));
        posicion = getIntent().getIntExtra("posicion", -1);
    }

    public void initreferences() {
        etAutor = findViewById(R.id.etAutorModificar);
        etTitulo = findViewById(R.id.etTituloModificar);
        etEditorial = findViewById(R.id.etEditorialModificar);
        etPaginas = findViewById(R.id.etPaginasModificar);
        etTop3 = findViewById(R.id.etTop3Modificar);
        btModificar = findViewById(R.id.btModificarLibro);
    }

}
