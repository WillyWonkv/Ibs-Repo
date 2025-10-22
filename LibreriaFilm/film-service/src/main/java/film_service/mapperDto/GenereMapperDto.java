package film_service.mapperDto;

import film_service.dto.AttoreDto;
import film_service.dto.FilmDto;
import film_service.dto.GenereDto;
import film_service.dto.RegistaDto;
import film_service.entity.Genere;

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
