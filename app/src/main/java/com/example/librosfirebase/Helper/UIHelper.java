package com.example.librosfirebase.Helper;

import com.example.librosfirebase.Pojos.Libro;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class UIHelper {
    //Creamos el FirebaseRecyclerOptions para configurar el adaptador
    private FirebaseRecyclerOptions<Libro> configAdaptador;
    private Query libroPaginas, primerLibroEditorial, listaLibros;

    public UIHelper() {
        //Query que retorna el libro que tiene más páginas.
        libroPaginas = FirebaseDatabase.getInstance().getReference().child("libros").orderByChild("paginas").limitToLast(1);
        //Query que retorna el primer libro de la editorial "ALFAGUARA"
        primerLibroEditorial = FirebaseDatabase.getInstance().getReference().child("libros").orderByChild("editorial").equalTo("ALFAGUARA").limitToLast(1);
        //
        //Query que sirve para que muestre todos los libros ordenados por el autor
        listaLibros = FirebaseDatabase.getInstance().getReference().child("libros").limitToLast(40);
        setConfAdaptador(listaLibros);
    }

    public FirebaseRecyclerOptions<Libro> getConfAdaptador() {
        return configAdaptador;
    }

    /**
     * Set del FirebaseRecyclerOptions que configura el adaptador para los libros con el que luego
     * haremos la llamada para que haga la query y nos devuelva los datos.
     * @param query
     */
    public void setConfAdaptador(Query query) {
        this.configAdaptador = new FirebaseRecyclerOptions.Builder<Libro>().setQuery(query, Libro.class).build();
    }

    public Query getLibrosPaginas() {
        return libroPaginas;
    }

    public Query getLibrosEditorial() {
        return primerLibroEditorial;
    }
}
