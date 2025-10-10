package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.Film;
import com.example.libreriafilm.entity.Utente;


import java.util.Date;

public record PrestitoDto(long id, Date dataPrestito, Date dataRestituzione, FilmDto film, UtenteDto utente) {
}
