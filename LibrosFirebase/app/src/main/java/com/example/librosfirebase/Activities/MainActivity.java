package com.example.librosfirebase.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.librosfirebase.Adaptadores.InterfazAdaptadorLibros;
import com.example.librosfirebase.Adaptadores.LibroFirebaseAdapterUI;
import com.example.librosfirebase.Helper.UIHelper;
import com.example.librosfirebase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase principal en la que se muestra la RecyclerView con los libros.
 */
public class MainActivity extends AppCompatActivity {
    public static final String POSICION_LIBRO = "posicion";
    private RecyclerView listaLibros;
    private static InterfazAdaptadorLibros adaptadorLibros;

    private FloatingActionButton aniadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initreferences();
        setRecyclerAdapter();
        listaLibros.setLayoutManager(new LinearLayoutManager(this));
        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que abriria la activity para añadir un libro
                aniadirLibro();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consultas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_LibrosAutor:
                consultaAutor();
                return true;
            case R.id.menu_librosPaginas:
                consultaPaginas();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static InterfazAdaptadorLibros getAdaptadorLibros (){
        return adaptadorLibros;
    }

    private void setRecyclerAdapter(){
        adaptadorLibros = new LibroFirebaseAdapterUI(new UIHelper());
        adaptadorLibros.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Método que abre la activity para ver y modificar el libro
                verLibro(listaLibros.getChildLayoutPosition(v));
            }
        });
        adaptadorLibros.startListening();
        listaLibros.setAdapter(adaptadorLibros.toRecyclerAdapter());
    }

    public void verLibro(int posicion){
        Intent intentVer = new Intent(this, MainVerLibro.class);
        intentVer.putExtra(POSICION_LIBRO, posicion);
        startActivity(intentVer);
    }

    public void aniadirLibro(){
        Intent intentAniadir = new Intent(this, MainAniadirLibro.class);
        startActivity(intentAniadir);
    }

    public void consultaAutor(){
        Intent intentConsultaAutor = new Intent(this, MainConsultas.class);
        intentConsultaAutor.putExtra("posicion", 1);
        startActivity(intentConsultaAutor);
    }

    public void consultaPaginas(){
        Intent intentConsultaPaginas = new Intent(this, MainConsultas.class);
        intentConsultaPaginas.putExtra("poscion", 2);
        startActivity(intentConsultaPaginas);
    }

    public void initreferences(){
        listaLibros = findViewById(R.id.rvListaLibros);
        aniadir = findViewById(R.id.aniadir);
    }

}
