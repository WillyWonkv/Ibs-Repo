package com.example.libreriafilm.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @ManyToMany(mappedBy = "generi")
    private List<Film> film;

    public Genere(String nome) {
        this.nome = nome;
    }

    public Genere() {}

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

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }
}
