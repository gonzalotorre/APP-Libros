package com.example.librosfirebase.GestionLibros;

import androidx.annotation.NonNull;

import com.example.librosfirebase.Helper.UIHelper;
import com.example.librosfirebase.Interfaces.InterfazLibroUI;
import com.example.librosfirebase.Pojos.Libro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LibrosFirebase implements InterfazLibroUI {

    //Constante para acceder al nodo libros de la base de datos
    public static final String NODO = "libros";
    public DatabaseReference myRef;
    public FirebaseDatabase database;
    public UIHelper firebaseManager = new UIHelper();

    public LibrosFirebase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(NODO);
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    @Override
    public void aniadirLibro(Libro libro) {
        myRef.push().setValue(libro);
    }

    @Override
    public void borrarLibro(String idLibro) {
        myRef.child(idLibro).removeValue();
    }

    @Override
    public void modificarLibro(Libro libro, String idLibro) {
        myRef.child(idLibro).setValue(libro);
    }

    /*@Override
    public void guardarLibro(String id, Libro libro) {
        myRef.child(id).setValue(libro);
    }*/

    /**
     * Método que guarda en una lista los libros ordenados por páginas
     * @param escuchador
     */
    @Override
    public void librosPaginas(final EscuchadorLibroPaginas escuchador) {
        Query queryPags = firebaseManager.getLibrosPaginas();
        queryPags.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot libroSnapshot : dataSnapshot.getChildren()) {
                    Libro libro = libroSnapshot.getValue(Libro.class);
                    escuchador.onResponse(libro);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchador.onError(databaseError);
            }
        });
    }

    /**
     * Método que guarda el libro de autor Dan Brown con más páginas
     * @param escuchador
     */
    @Override
    public void libroEditorial(final EscuchadorLibroAutor escuchador) {
        Query queryAutor = firebaseManager.getLibrosEditorial();
        queryAutor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot libroSnapshot : dataSnapshot.getChildren()){
                    Libro libroEditorial = libroSnapshot.getValue(Libro.class);
                    escuchador.onResponseAutor(libroEditorial);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchador.onErrorAutor(databaseError);
            }
        });
    }


}
