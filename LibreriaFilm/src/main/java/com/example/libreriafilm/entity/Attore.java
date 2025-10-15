package com.example.libreriafilm.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Attore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataNascita;

    @ManyToMany(mappedBy = "attori",  fetch = FetchType.LAZY)
    private List<Film> film;

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

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }
}
