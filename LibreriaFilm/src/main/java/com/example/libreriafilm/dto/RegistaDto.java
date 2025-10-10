package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.Film;

import java.util.Date;
import java.util.List;

public record RegistaDto(long id, String nome, Date dataNascita, List<FilmDto> film) {
}
