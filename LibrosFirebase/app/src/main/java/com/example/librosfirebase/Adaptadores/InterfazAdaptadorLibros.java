package com.example.librosfirebase.Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;

public interface InterfazAdaptadorLibros {

    //lo usa Firebase
    String getKey(int pos);
    Libro getItem(int pos);
    void startListening();
    void stopListening();
    void setOnItemClickListener(View.OnClickListener onClick);
    RecyclerView.Adapter toRecyclerAdapter();


    class LibroHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvAutor, tvTop3, tvEditorial, tvPaginas, tvCategorias;

        LibroHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloAutor);
            tvAutor = itemView.findViewById(R.id.tvAutorAutor);
            tvTop3 = itemView.findViewById(R.id.tvTop3Autor);
            tvEditorial = itemView.findViewById(R.id.tvEditorialAutor);
            tvPaginas = itemView.findViewById(R.id.tvPaginasAutor);
        }

        void bindLibro(Libro libro) {
            tvTitulo.setText(libro.getTitulo());
            tvAutor.setText(libro.getAutor());
            tvTop3.setText(String.valueOf(libro.isTop3()));
            tvEditorial.setText(libro.getEditorial());
            tvPaginas.setText(String.valueOf(libro.getPaginas()));
        }
    }

}
