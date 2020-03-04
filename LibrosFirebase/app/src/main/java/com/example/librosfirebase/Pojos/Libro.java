package com.example.librosfirebase.Pojos;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private String autor, titulo, editorial;
    private boolean top3;
    private int paginas;
    private List<String> categorias, valoraciones;

    public Libro() {
    }

    public Libro(String autor, String titulo, String editorial, boolean top3, int paginas, List<String> categorias, List<String> valoraciones) {
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.top3 = top3;
        this.paginas = paginas;
        this.categorias = categorias;
        this.valoraciones = valoraciones;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean isTop3() {
        return top3;
    }

    public void setTop3(boolean top3) {
        this.top3 = top3;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<String> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", top3=" + top3 +
                ", paginas=" + paginas +
                ", categorias=" + categorias +
                ", valoraciones=" + valoraciones +
                '}';
    }
}
