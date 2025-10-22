package film_service.mapperDto;

import film_service.dto.AttoreDto;
import film_service.dto.FilmDto;
import film_service.dto.GenereDto;
import film_service.dto.RegistaDto;
import film_service.entity.Film;

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
                        ).toList());

    }

    public static Film newFilm(FilmDto filmDto){

        Film film = new Film();
        film.setTitolo(filmDto.titolo());
        film.setDescrizione(filmDto.descrizione());
        film.setPrezzo(filmDto.prezzo());
        film.setAnnoUscita(filmDto.annoUscita());
        film.setDurata(filmDto.durata());

        return film;

    }

}
