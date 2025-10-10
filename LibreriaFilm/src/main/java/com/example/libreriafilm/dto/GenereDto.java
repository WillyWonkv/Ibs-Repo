package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.FilmGenere;

import java.util.List;

public record GenereDto(long id, String nome, List<FilmDto> film) {
}
