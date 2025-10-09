package com.example.biblioteca.dto;

import java.util.Date;

public class UtenteDto {

    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Date dataRegistrazione;

    public UtenteDto(long id, String nome, String cognome, String email, String password, Date dataRegistrazione) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataRegistrazione = dataRegistrazione;
    }

    public UtenteDto(long id, String nome, String cognome) {
        this(id,nome,cognome,null,null,null);
    }

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }
}
