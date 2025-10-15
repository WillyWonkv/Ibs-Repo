package com.example.libreriafilm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record FilmDto(long id, String titolo, String descrizione, double prezzo, int annoUscita, int durata, RegistaDto regista, List<AttoreDto> attori, List<GenereDto> generi, List<PrestitoDto> prestito) {
}
