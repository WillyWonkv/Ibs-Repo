package com.example.libreriafilm.MapperDto;

import com.example.libreriafilm.dto.AttoreDto;
import com.example.libreriafilm.dto.FilmDto;
import com.example.libreriafilm.dto.GenereDto;
import com.example.libreriafilm.dto.RegistaDto;
import com.example.libreriafilm.entity.Genere;

public class GenereMapperDto {

    public static GenereDto genereToGenereDto(Genere g) {

        return new GenereDto(
                g.getId(),
                g.getNome(),
                g.getFilm().stream()
                        .map(f -> new FilmDto(
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
                                        .map(a -> new AttoreDto(
                                                a.getId(),
                                                a.getNome(),
                                                a.getDataNascita(),
                                                null
                                        )).toList(),
                                null,
                                null
                        )).toList()
        );

    }

    public static Genere newGenere(GenereDto genereDto) {

        Genere genere = new Genere();
        genere.setNome(genereDto.nome());
        return genere;

    }

}
