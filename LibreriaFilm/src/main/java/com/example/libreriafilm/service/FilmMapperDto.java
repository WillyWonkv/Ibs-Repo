package com.example.libreriafilm.service;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Film;

public class FilmMapperDto {

    public static FilmDto FilmToFilmDto(Film f){

        return new FilmDto(
                f.getId(),
                f.getTitolo(),
                f.getDescrizione(),
                f.getPrezzo(),
                f.getAnnoUscita(),
                f.getDurata(),
                new RegistaDto(
                        f.getRegista().getId(),
                        f.getRegista().getNome(),
                        f.getRegista().getDataNascita(),
                        null
                ),
                f.getAttori().stream()
                        .map(
                                a -> new AttoreDto(
                                        a.getId(),
                                        a.getNome(),
                                        a.getDataNascita(),
                                        null
                                )
                        ).toList(),
                f.getGeneri().stream()
                        .map(
                                g -> new GenereDto(
                                        g.getId(),
                                        g.getNome(),
                                        null
                                )
                        ).toList(),
                null);

    }

}
