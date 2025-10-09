package com.example.biblioteca.dto;

import java.util.Date;

public class PrestitoLibroDto {

    private long id;
    private Date dataPrestito;
    private Date dataRestitituzione;
    private UtenteDto utenteDto;

    public PrestitoLibroDto(long id, Date dataPrestito, Date dataRestitituzione, UtenteDto utenteDto) {
        this.id = id;
        this.dataPrestito = dataPrestito;
        this.dataRestitituzione = dataRestitituzione;
        this.utenteDto = utenteDto;
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

    public UtenteDto getUtenteDto() {
        return utenteDto;
    }

    public void setUtenteDto(UtenteDto utenteDto) {
        this.utenteDto = utenteDto;
    }
}
