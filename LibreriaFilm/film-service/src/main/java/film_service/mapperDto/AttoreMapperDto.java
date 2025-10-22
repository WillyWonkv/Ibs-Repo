package film_service.mapperDto;

import film_service.dto.AttoreDto;
import film_service.dto.FilmDto;
import film_service.dto.GenereDto;
import film_service.dto.RegistaDto;
import film_service.entity.Attore;

public class AttoreMapperDto {

    public static AttoreDto attoreToAttoreDto(Attore a) {

        return new AttoreDto(
                a.getId(),
                a.getNome(),
                a.getDataNascita(),
                a.getFilm().stream()
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
                                null,
                                f.getGeneri().stream()
                                        .map(g -> new GenereDto(
                                                g.getId(),
                                                g.getNome(),
                                                null
                                        )).toList()
                        )).toList()
        );

    }

    public static Attore newAttore(AttoreDto attoreDto) {

        Attore attore = new Attore();
        attore.setNome(attoreDto.nome());
        attore.setDataNascita(attoreDto.dataNascita());
        return attore;

    }

}
