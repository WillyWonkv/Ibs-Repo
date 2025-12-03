package com.example.LoginProject.dto.mapperDTO;

import com.example.LoginProject.dto.FilmDTO;
import com.example.LoginProject.dto.GenreDTO;
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
                        .map(g -> {
                            GenreDTO genreDTO = new GenreDTO();
                            genreDTO.setId(g.getId());
                            genreDTO.setName(g.getName());
                            return genreDTO;
                        })
                .collect(Collectors.toSet())
        );

    }

    public static GenreDTO mapGenreToGenreDTO(Genre genre){
        return new GenreDTO(
                genre.getId(),
                genre.getName()
        );
    }

}
