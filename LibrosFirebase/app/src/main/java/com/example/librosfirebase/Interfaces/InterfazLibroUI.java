package com.example.librosfirebase.Interfaces;

import com.example.librosfirebase.Pojos.Libro;
import com.google.firebase.database.DatabaseError;

import java.util.List;

/**
 * Interfaz que contendrá todos los métodos para realizar las operaciones que se piden.
 */
public interface InterfazLibroUI {

    void aniadirLibro(Libro libro);
    void borrarLibro(String idLibro);
    void modificarLibro(Libro libro, String idLibro);

    interface EscuchadorLibroPaginas{
        void onResponse(Libro libro);
        void onError(DatabaseError error);
    }

    interface EscuchadorLibroAutor{
        void onResponseAutor(Libro libro);
        void onErrorAutor(DatabaseError error);;

    }

    //Para realizar la consulta: libros ordenados por las páginas
    void librosPaginas(EscuchadorLibroPaginas escuchador);
    //Para realizar la consulta: libros escritos por un determinado autor
    void libroEditorial(EscuchadorLibroAutor escuchador);
}
