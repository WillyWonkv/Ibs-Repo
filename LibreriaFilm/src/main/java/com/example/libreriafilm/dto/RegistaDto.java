package com.example.libreriafilm.dto;

import com.example.libreriafilm.entity.Film;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RegistaDto(long id, @NotBlank String nome, Date dataNascita, List<FilmDto> film) {
}
