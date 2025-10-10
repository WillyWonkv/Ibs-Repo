package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.FilmAttore;
import com.example.libreriafilm.entity.FilmGenere;
import com.example.libreriafilm.entity.Regista;

import java.util.List;

public record FilmDto(long id, String titolo, String descrizione, double prezzo, int annoUscita, int durata, RegistaDto regista, List<AttoreDto> attore, List<GenereDto> genere) {
}
