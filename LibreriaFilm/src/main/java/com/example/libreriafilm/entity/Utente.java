package com.example.libreriafilm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private double soldi;

    @Temporal(TemporalType.DATE)
    private Date dataRegistrazione;

    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
    private List<Prestito> prestito;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "utente_ruolo",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id")
    )
    private Set<Ruolo> ruolo = new HashSet<>();

}
