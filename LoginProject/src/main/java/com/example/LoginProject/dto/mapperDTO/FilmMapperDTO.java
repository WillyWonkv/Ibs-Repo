package com.example.LoginProject.dto.mapperDTO;

import com.example.LoginProject.dto.FilmDTO;
import com.example.LoginProject.entity.Film;
import com.example.LoginProject.entity.Genre;

import java.util.stream.Collectors;

public class FilmMapperDTO {

    public static FilmDTO mapFilmToFilmDTO(Film film){

        return new FilmDTO(
                film.getId(),
                film.getCoverSrc(),
                film.getTitle(),
                film.getDescription(),
                film.getDuration(),
                film.getGenres().stream()
                        .map(Genre::getId)
                        .collect(Collectors.toSet())
        );

    }

}
