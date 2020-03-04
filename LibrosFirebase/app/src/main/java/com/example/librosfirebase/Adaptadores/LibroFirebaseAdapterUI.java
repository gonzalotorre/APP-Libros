package com.example.librosfirebase.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librosfirebase.Helper.UIHelper;
import com.example.librosfirebase.Pojos.Libro;
import com.example.librosfirebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class LibroFirebaseAdapterUI
        extends FirebaseRecyclerAdapter<Libro, InterfazAdaptadorLibros.LibroHolder>
        implements InterfazAdaptadorLibros{

    private View.OnClickListener onClickListener;

    public LibroFirebaseAdapterUI(UIHelper firebaseHelper) {
        super(firebaseHelper.getConfAdaptador());
    }

    @Override
    protected void onBindViewHolder(@NonNull LibroHolder holder, int position, @NonNull Libro libro) {
        holder.bindLibro(libro);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @NonNull
    @Override
    public LibroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista, parent, false);
        return new LibroHolder(view);
    }

    @Override
    public String getKey(int pos) {
        return super.getSnapshots().getSnapshot(pos).getKey();
    }

    @Override
    public void setOnItemClickListener(View.OnClickListener onClick) {
        this.onClickListener = onClick;
    }

    @Override
    public RecyclerView.Adapter toRecyclerAdapter() {
        return this;
    }


}
