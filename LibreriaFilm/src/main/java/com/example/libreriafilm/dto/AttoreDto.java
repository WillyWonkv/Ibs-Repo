package com.example.libreriafilm.dto;

import java.util.Date;
import java.util.List;

public record AttoreDto(long id, String nome, Date dataNascita, List<FilmDto> film) {
}
