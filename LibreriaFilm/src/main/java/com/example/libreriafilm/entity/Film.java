package com.example.libreriafilm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "regista_id")
    private Regista regista;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "film_attore",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "attore_id")
    )
    private List<Attore> attori;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "film_genere",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genere_id")
    )
    private List<Genere> generi;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Prestito> prestito;


}
