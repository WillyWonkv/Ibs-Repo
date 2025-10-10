package com.example.libreriafilm.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToMany(mappedBy = "genere",  fetch = FetchType.LAZY)
    private List<FilmGenere> film;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<FilmGenere> getFilm() {
        return film;
    }

    public void setFilm(List<FilmGenere> film) {
        this.film = film;
    }
}
