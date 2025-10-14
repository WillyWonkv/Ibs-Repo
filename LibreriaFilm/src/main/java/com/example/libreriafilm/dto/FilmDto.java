package com.example.libreriafilm.dto;

import java.util.List;

public record FilmDto(long id, String titolo, String descrizione, double prezzo, int annoUscita, int durata, RegistaDto regista, List<AttoreDto> attori, List<GenereDto> generi, List<PrestitoDto> prestito) {
}
