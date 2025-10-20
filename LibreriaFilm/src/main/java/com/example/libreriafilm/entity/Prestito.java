package com.example.libreriafilm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date dataPrestito;

    @Temporal(TemporalType.DATE)
    private Date dataRestituzione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id")
    private Utente utente;

}
