package com.example.libreriafilm.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titolo;

    private String descrizione;

    private double prezzo;

    private int annoUscita;

    private int durata;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "regista_id")
    private Regista regista;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmAttore> attore;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<FilmGenere> genere;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Prestito> prestito;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getAnnoUscita() {
        return annoUscita;
    }

    public void setAnnoUscita(int annoUscita) {
        this.annoUscita = annoUscita;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public Regista getRegista() {
        return regista;
    }

    public void setRegista(Regista regista) {
        this.regista = regista;
    }

    public List<FilmAttore> getAttore() {
        return attore;
    }

    public void setAttore(List<FilmAttore> attore) {
        this.attore = attore;
    }

    public List<FilmGenere> getGenere() {
        return genere;
    }

    public void setGenere(List<FilmGenere> genere) {
        this.genere = genere;
    }

    public List<Prestito> getPrestito() {
        return prestito;
    }

    public void setPrestito(List<Prestito> prestito) {
        this.prestito = prestito;
    }


}
