package com.example.libreriafilm.entity;

import jakarta.persistence.*;

@Entity
public class FilmAttore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attore_id")
    private Attore attore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Attore getAttore() {
        return attore;
    }

    public void setAttore(Attore attore) {
        this.attore = attore;
    }


}
