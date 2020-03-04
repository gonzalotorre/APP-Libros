package com.example.librosfirebase.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librosfirebase.Adaptadores.InterfazAdaptadorLibros;
import com.example.librosfirebase.GestionLibros.LibrosFirebase;
import com.example.librosfirebase.Interfaces.InterfazLibroUI;
import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;

public class MainVerLibro extends AppCompatActivity {

    private TextView tvAutor, tvTitulo, tvEditorial, tvPaginas, tvTop3;
    private Button btBorrar, btModificar;
    private InterfazAdaptadorLibros adaptadorLibros;
    private InterfazLibroUI libros;
    private Libro libro, libroModificado;
    private int posicion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_libro);
        initreferences();
        libros = new LibrosFirebase();
        //Obtenemos la posicion en la que está el libro
        posicion = getIntent().getIntExtra(MainActivity.POSICION_LIBRO, -1);
        //Obtenemos el libro que hay en la posicion clikada
        libro = MainActivity.getAdaptadorLibros().getItem(posicion);
        //Mostramos los datos del libro seleccionado
        mostrarDatosLibro();

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para borrar el libro, lo que necesitamos es la posicion que ocupa en la recycler view para
                //luego a través de ella obtener la key (que sería el id en firebase) del libro que vamos a borrar
                if (posicion != -1) {
                    libros.borrarLibro(MainActivity.getAdaptadorLibros().getKey(posicion));
                    finish();
                }
            }
        });

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirModificarLibro();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String autor = data.getStringExtra("autorModificado");
            tvAutor.setText(autor);
            String titulo = data.getStringExtra("tituloModificado");
            tvTitulo.setText(titulo);
            String editorial = data.getStringExtra("editorialModificado");
            tvEditorial.setText(editorial);
            int paginas = data.getIntExtra("paginasModificado", -1);
            tvPaginas.setText(String.valueOf(paginas));
            boolean top3 = data.getBooleanExtra("top3Modificado", true);
            tvTop3.setText(String.valueOf(top3));
            libroModificado = new Libro(autor, titulo, editorial, top3, paginas, null, null);
        }
    }

    public void abrirModificarLibro(){
        Intent intentModificar = new Intent(this, MainModificarLibro.class);
        intentModificar.putExtra("autor", libro.getAutor());
        intentModificar.putExtra("titulo", libro.getTitulo());
        intentModificar.putExtra("editorial", libro.getEditorial());
        intentModificar.putExtra("paginas", libro.getPaginas());
        intentModificar.putExtra("top3", libro.isTop3());
        intentModificar.putExtra("posicion", posicion);
        startActivityForResult(intentModificar, 1234);
    }

    /**
     * Método que muestra los datos del libro seleccionado.
     */
    public void mostrarDatosLibro() {
        tvAutor.setText(libro.getAutor());
        tvTitulo.setText(libro.getTitulo());
        tvEditorial.setText(libro.getEditorial());
        tvPaginas.setText(String.valueOf(libro.getPaginas()));
        tvTop3.setText(Boolean.toString(libro.isTop3()));
    }

    /**
     * Método para inicializar todos los atributos que necesitemos en el layout.
     */
    public void initreferences() {
        tvAutor = findViewById(R.id.tvAutorLibro);
        tvTitulo = findViewById(R.id.tvTituloLibro);
        tvEditorial = findViewById(R.id.tvEditorialLibro);
        tvPaginas = findViewById(R.id.tvPaginasLibro);
        tvTop3 = findViewById(R.id.tvTop3Libro);
        btBorrar = findViewById(R.id.btBorrar);
        btModificar = findViewById(R.id.btModificarLibro);
    }


}
