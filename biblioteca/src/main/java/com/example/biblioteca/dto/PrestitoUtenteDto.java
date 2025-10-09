package com.example.biblioteca.dto;

import java.util.Date;

public class PrestitoUtenteDto {

    private long id;
    private Date dataPrestito;
    private Date dataRestitituzione;
    private LibroDto libroDto;

    public PrestitoUtenteDto(long id, Date dataPrestito, Date dataRestitituzione, LibroDto libroDto) {
        this.id = id;
        this.dataPrestito = dataPrestito;
        this.dataRestitituzione = dataRestitituzione;
        this.libroDto = libroDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(Date dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public Date getDataRestitituzione() {
        return dataRestitituzione;
    }

    public void setDataRestitituzione(Date dataRestitituzione) {
        this.dataRestitituzione = dataRestitituzione;
    }

    public LibroDto getLibroDto() {
        return libroDto;
    }

    public void setLibroDto(LibroDto libroDto) {
        this.libroDto = libroDto;
    }

}
