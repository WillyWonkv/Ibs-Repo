package com.example.biblioteca.entity;

import jakarta.persistence.*;

import java.util.Date;

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
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Prestito(Date dataPrestito, Date dataRestituzione, Utente utente, Libro libro) {
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
        this.utente = utente;
        this.libro = libro;
    }

    public Prestito() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Date getDataRestituzione() {
        return dataRestituzione;
    }

    public void setDataRestituzione(Date dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    public Date getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(Date dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

}
