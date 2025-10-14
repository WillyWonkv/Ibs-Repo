package com.example.libreriafilm.dto;

import java.util.List;

public record GenereDto(long id, String nome, List<FilmDto> film) {
}
