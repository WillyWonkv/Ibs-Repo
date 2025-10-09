package com.example.biblioteca.dto;

import com.example.biblioteca.entity.Categoria;

public class LibroDto {

    private long id;
    private String titolo;
    private String autore;
    private String isbn;
    private int annoPubblicazione;
    private boolean isPrestito;
    private Categoria categoria;

    public LibroDto(long id, String titolo, String autore, String isbn, int annoPubblicazione, boolean isPrestito, Categoria categoria) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.annoPubblicazione = annoPubblicazione;
        this.isPrestito = isPrestito;
        this.categoria = categoria;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {}

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public boolean isPrestito() {
        return isPrestito;
    }

    public void setPrestito(boolean prestito) {
        isPrestito = prestito;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
